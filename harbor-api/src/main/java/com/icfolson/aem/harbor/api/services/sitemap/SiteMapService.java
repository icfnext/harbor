package com.icfolson.aem.harbor.api.services.sitemap;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMap;

public interface SiteMapService {

	SiteMap getSitemap(PageDecorator root);

}
