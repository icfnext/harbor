package com.icfolson.aem.harbor.api.services.sitemap;

import com.icfolson.aem.harbor.api.domain.sitemap.SiteMapEntry;
import com.icfolson.aem.library.api.page.PageDecorator;

import java.util.List;

public interface SiteMapEntryProvider {

    List<SiteMapEntry> getEntries(PageDecorator root);

}
