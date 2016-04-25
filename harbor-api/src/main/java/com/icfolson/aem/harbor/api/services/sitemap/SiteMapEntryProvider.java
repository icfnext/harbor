package com.icfolson.aem.harbor.api.services.sitemap;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;

import java.util.List;

public interface SiteMapEntryProvider {

    public List<SiteMapEntry> getEntries(PageDecorator root);

}
