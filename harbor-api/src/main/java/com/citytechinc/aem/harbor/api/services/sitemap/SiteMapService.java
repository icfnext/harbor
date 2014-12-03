package com.citytechinc.aem.harbor.api.services.sitemap;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.api.domain.sitemap.SiteMap;

public interface SiteMapService {

	SiteMap getSitemap(PageDecorator root);

}
