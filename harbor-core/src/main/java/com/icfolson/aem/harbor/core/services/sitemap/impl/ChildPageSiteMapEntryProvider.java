package com.icfolson.aem.harbor.core.services.sitemap.impl;

import com.day.cq.commons.Externalizer;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.harbor.api.services.sitemap.SiteMapEntryProvider;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.query.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.day.cq.commons.jcr.JcrConstants.JCR_CREATED;
import static com.day.cq.wcm.api.NameConstants.PN_PAGE_LAST_MOD;
import static com.icfolson.aem.namespace.api.ontology.Properties.ICF_OLSON_SITEMAP_CHANGE_FREQUENCY;
import static com.icfolson.aem.namespace.api.ontology.Properties.ICF_OLSON_SITEMAP_PRIORITY;

@Service
@Component
public class ChildPageSiteMapEntryProvider implements SiteMapEntryProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ChildPageSiteMapEntryProvider.class);

    private static final String PAGES_WHICH_CAN_BE_MAPPED_QUERY = "SELECT * FROM [cq:Page] WHERE ISDESCENDANTNODE('{rootpath}')";

    private static SimpleDateFormat ISO_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Property(label = "Externalizer Name", value = "publish")
    private static final String EXTERNALIZER_NAME = "externalizerName";

    private String externalizerName;

    @Property(label = "Enabled", boolValue = false)
    private static final String PROVIDER_DISABLED = "providerDisabled";

    private Boolean disabled;

    @Reference
    private Externalizer externalizer;

    @Override
    public List<SiteMapEntry> getEntries(final PageDecorator root) {
        List<SiteMapEntry> entries = Lists.newArrayList();

        if (disabled) {
            return entries;
        }

        ResourceResolver resourceResolver = root.getContentResource().getResourceResolver();

        Iterator<Resource> pageResourceIterator = resourceResolver.findResources(
            PAGES_WHICH_CAN_BE_MAPPED_QUERY.replace("{rootpath}", root.getPath()), Query.JCR_SQL2);

        if (!root.getProperties().get(Properties.ICF_OLSON_HIDDEN_FROM_ROBOTS, false)) {
            entries.add(getSiteMapEntryForPage(root));
        }

        while (pageResourceIterator.hasNext()) {
            Resource currentPageResource = pageResourceIterator.next();

            PageDecorator currentPage = currentPageResource.adaptTo(PageDecorator.class);

            if (!currentPage.getProperties().get(Properties.ICF_OLSON_HIDDEN_FROM_ROBOTS, false)) {
                entries.add(getSiteMapEntryForPage(currentPage));
            }
        }

        return entries;
    }

    @Activate
    @Modified
    protected void activate(final Map<String, Object> properties) {
        externalizerName = PropertiesUtil.toString(properties.get(EXTERNALIZER_NAME), "publish");
        disabled = PropertiesUtil.toBoolean(properties.get(PROVIDER_DISABLED), true);
    }

    private SiteMapEntry getSiteMapEntryForPage(final PageDecorator page) {
        return new SiteMapEntry(
            determineLocForPage(page),
            determineLastModForPage(page),
            determineChangeFrequencyForPage(page),
            determinePriorityForPage(page));
    }

    private String determineLocForPage(final PageDecorator page) {
        final ResourceResolver resourceResolver = page.getContentResource().getResourceResolver();

        return this.externalizer.externalLink(resourceResolver, externalizerName,
            resourceResolver.map(page.getPath())) + ".html";
    }

    private String determineLastModForPage(final PageDecorator page) {
        final ValueMap pageProperties = page.getProperties();
        final Calendar lastModified = pageProperties.get(PN_PAGE_LAST_MOD,
            pageProperties.get(JCR_CREATED, Calendar.class));

        return lastModified == null ? null : ISO_DATE_FORMATTER.format(lastModified.getTime());

    }

    private String determineChangeFrequencyForPage(final PageDecorator page) {
        final String changeFrequencyValue = page.getProperties().get(ICF_OLSON_SITEMAP_CHANGE_FREQUENCY, String.class);

        if (changeFrequencyValue != null) {
            try {
                return ChangeFrequency.valueOf(changeFrequencyValue).name();
            } catch (IllegalArgumentException e) {
                LOG.error("Invalid Change Frequency of " + changeFrequencyValue + " found for page " + page.getPath());
                return null;
            }
        }

        return null;
    }

    private String determinePriorityForPage(final PageDecorator page) {
        final Double priorityValue = page.getProperties().get(ICF_OLSON_SITEMAP_PRIORITY, Double.class);

        return priorityValue != null ? priorityValue.toString() : null;
    }
}
