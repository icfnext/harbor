package com.icfolson.aem.harbor.core.services.sitemap.impl;

import com.day.cq.commons.Externalizer;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.harbor.api.services.sitemap.SiteMapEntryProvider;
import com.icfolson.aem.harbor.core.components.page.sitemappedpage.SitemappedPage;
import com.icfolson.aem.library.api.page.PageDecorator;
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

import javax.jcr.query.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.day.cq.commons.jcr.JcrConstants.JCR_CREATED;
import static com.day.cq.wcm.api.NameConstants.PN_PAGE_LAST_MOD;

@Service
@Component
public class ChildPageSiteMapEntryProvider implements SiteMapEntryProvider {

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
        final List<SiteMapEntry> entries = Lists.newArrayList();

        if (!disabled) {
            final ResourceResolver resourceResolver = root.getContentResource().getResourceResolver();

            final Iterator<Resource> resources = resourceResolver.findResources(
                PAGES_WHICH_CAN_BE_MAPPED_QUERY.replace("{rootpath}", root.getPath()), Query.JCR_SQL2);

            final SitemappedPage rootSitemappedPage = root.getContentResource().adaptTo(SitemappedPage.class);

            if (!rootSitemappedPage.isHiddenFromRobots()) {
                entries.add(getSiteMapEntryForPage(rootSitemappedPage));
            }

            while (resources.hasNext()) {
                final SitemappedPage sitemappedPage = resources.next().adaptTo(SitemappedPage.class);

                if (!sitemappedPage.isHiddenFromRobots()) {
                    entries.add(getSiteMapEntryForPage(sitemappedPage));
                }
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

    private SiteMapEntry getSiteMapEntryForPage(final SitemappedPage sitemappedPage) {
        return new SiteMapEntry(
            determineLocForPage(sitemappedPage),
            determineLastModForPage(sitemappedPage),
            determineChangeFrequencyForPage(sitemappedPage),
            determinePriorityForPage(sitemappedPage));
    }

    private String determineLocForPage(final SitemappedPage sitemappedPage) {
        final PageDecorator page = sitemappedPage.getPage();
        final ResourceResolver resourceResolver = page.getContentResource().getResourceResolver();

        return externalizer.externalLink(resourceResolver, externalizerName, resourceResolver.map(page.getHref()));
    }

    private String determineLastModForPage(final SitemappedPage sitemappedPage) {
        final ValueMap pageProperties = sitemappedPage.getPage().getProperties();
        final Calendar lastModified = pageProperties.get(PN_PAGE_LAST_MOD,
            pageProperties.get(JCR_CREATED, Calendar.class));

        return lastModified == null ? null : ISO_DATE_FORMATTER.format(lastModified.getTime());
    }

    private String determineChangeFrequencyForPage(final SitemappedPage sitemappedPage) {
        final ChangeFrequency changeFrequency = sitemappedPage.getChangeFrequency();

        return changeFrequency == null ? null : changeFrequency.name().toLowerCase();
    }

    private String determinePriorityForPage(final SitemappedPage sitemappedPage) {
        return sitemappedPage.getPriority();
    }
}
