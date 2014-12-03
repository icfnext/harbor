package com.citytechinc.aem.harbor.api.services.sitemap;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.api.domain.sitemap.SiteMapEntry;

import java.util.List;

public interface SiteMapEntryProvider {

    public List<SiteMapEntry> getEntries(PageDecorator root);

}
