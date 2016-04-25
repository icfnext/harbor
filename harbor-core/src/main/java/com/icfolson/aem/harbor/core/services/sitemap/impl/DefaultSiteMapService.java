package com.icfolson.aem.harbor.core.services.sitemap.impl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.icfolson.aem.harbor.api.services.sitemap.SiteMapEntryProvider;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.felix.scr.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMap;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.harbor.api.services.sitemap.SiteMapService;

@Service
@Component(name = "Site Map Service", label = "Site Map Service")
public class DefaultSiteMapService implements SiteMapService {

	protected static final Logger LOG = LoggerFactory.getLogger(DefaultSiteMapService.class);

    @Reference(cardinality = ReferenceCardinality.MANDATORY_MULTIPLE, policy = ReferencePolicy.DYNAMIC, bind = "bindSiteMapEntryProvider", unbind = "unbindSiteMapEntryProvider", referenceInterface = SiteMapEntryProvider.class)
    private Set<SiteMapEntryProvider> siteMapEntryProviders = Sets.newHashSet();

    protected ReentrantReadWriteLock siteMapEntryProvidersLock = new ReentrantReadWriteLock(false);

    @Override
    public SiteMap getSitemap(PageDecorator root) {
        List<SiteMapEntry> entries = Lists.newArrayList();

        for (SiteMapEntryProvider currentSiteMapEntryProvider : getSiteMapEntryProviders()) {
            entries.addAll(currentSiteMapEntryProvider.getEntries(root));
        }

        return new SiteMap(entries);
    }

    protected Set<SiteMapEntryProvider> getSiteMapEntryProviders() {
        siteMapEntryProvidersLock.readLock().lock();

        try {
            return Sets.newHashSet(siteMapEntryProviders);
        } finally {
            siteMapEntryProvidersLock.readLock().unlock();
        }
    }

    protected void bindSiteMapEntryProvider(SiteMapEntryProvider provider) {
        siteMapEntryProvidersLock.writeLock().lock();

        try {
            siteMapEntryProviders.add(provider);
        } finally {
            siteMapEntryProvidersLock.writeLock().unlock();
        }
    }

    protected void unbindSiteMapEntryProvider(SiteMapEntryProvider provider) {
        siteMapEntryProvidersLock.writeLock().lock();

        try {
            siteMapEntryProviders.remove(provider);
        } finally {
            siteMapEntryProvidersLock.writeLock().unlock();
        }
    }


}
