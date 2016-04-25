package com.icfolson.aem.harbor.core.services.sitemap.impl;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.harbor.api.services.sitemap.SiteMapEntryProvider;
import com.citytechinc.aem.namespace.api.ontology.Properties;
import com.day.cq.commons.Externalizer;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.query.Query;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.citytechinc.aem.namespace.api.ontology.Properties.CITYTECH_SITEMAP_CHANGE_FREQUENCY;
import static com.citytechinc.aem.namespace.api.ontology.Properties.CITYTECH_SITEMAP_PRIORITY;
import static com.day.cq.commons.jcr.JcrConstants.JCR_CREATED;
import static com.day.cq.wcm.api.NameConstants.PN_PAGE_LAST_MOD;

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
    public List<SiteMapEntry> getEntries(PageDecorator root) {

        List<SiteMapEntry> entries = Lists.newArrayList();

        if (disabled) {
            return entries;
        }

        ResourceResolver resourceResolver = root.getContentResource().getResourceResolver();

        Iterator<Resource> pageResourceIterator = resourceResolver.findResources(PAGES_WHICH_CAN_BE_MAPPED_QUERY.replace("{rootpath}", root.getPath()), Query.JCR_SQL2);

        if (!root.getProperties().get(Properties.CITYTECH_HIDDEN_FROM_ROBOTS, false)) {
            entries.add(getSiteMapEntryForPage(root));
        }

        while (pageResourceIterator.hasNext()) {
            Resource currentPageResource = pageResourceIterator.next();

            PageDecorator currentPage = currentPageResource.adaptTo(PageDecorator.class);

            if (!currentPage.getProperties().get(Properties.CITYTECH_HIDDEN_FROM_ROBOTS, false)) {
                entries.add(getSiteMapEntryForPage(currentPage));
            }
        }

        return entries;

    }

    @Activate
    @Modified
    protected void activate( Map<String, Object> properties ) {
        externalizerName = PropertiesUtil.toString(properties.get(EXTERNALIZER_NAME), "publish");
        disabled = PropertiesUtil.toBoolean(properties.get(PROVIDER_DISABLED), true);
    }

    private SiteMapEntry getSiteMapEntryForPage(PageDecorator page) {
        return new SiteMapEntry(
                determineLocForPage(page),
                determineLastModForPage(page),
                determineChangeFrequencyForPage(page),
                determinePriorityForPage(page));
    }

    private String determineLocForPage(PageDecorator page) {
        ResourceResolver resourceResolver = page.getContentResource().getResourceResolver();
        return this.externalizer.externalLink(resourceResolver, externalizerName, resourceResolver.map(page.getPath())) + ".html";
    }

    private String determineLastModForPage(PageDecorator page) {

        ValueMap pageProperties = page.getProperties();
        Calendar lastModified = pageProperties.get(PN_PAGE_LAST_MOD, pageProperties.get(JCR_CREATED, Calendar.class));

        if (lastModified == null) {
            return null;
        }

        return ISO_DATE_FORMATTER.format(lastModified.getTime());

    }

    private String determineChangeFrequencyForPage(PageDecorator page) {
        String changeFrequencyValue = page.getProperties().get(CITYTECH_SITEMAP_CHANGE_FREQUENCY, String.class);

        if (changeFrequencyValue != null) {
            try {
                return ChangeFrequency.valueOf(changeFrequencyValue).name();
            }
            catch (IllegalArgumentException e) {
                LOG.error("Invalid Change Frequency of " + changeFrequencyValue + " found for page " + page.getPath());
                return null;
            }
        }

        return null;
    }

    private String determinePriorityForPage(PageDecorator page) {
        Double priorityValue = page.getProperties().get(CITYTECH_SITEMAP_PRIORITY, Double.class);

        if (priorityValue != null) {
            return priorityValue.toString();
        }

        return null;
    }

}
