package com.citytechinc.cq.harbor.proper.api.services.sitemap;

import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;
import com.citytechinc.cq.library.content.page.PageDecorator;

public interface SiteMapService {

    SiteMap getSitemapEntryList(PageDecorator root);

}
