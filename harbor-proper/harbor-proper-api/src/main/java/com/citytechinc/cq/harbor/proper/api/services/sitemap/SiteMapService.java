package com.citytechinc.cq.harbor.proper.api.services.sitemap;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;

public interface SiteMapService {

	SiteMap getSitemapEntryList(PageDecorator root);

}
