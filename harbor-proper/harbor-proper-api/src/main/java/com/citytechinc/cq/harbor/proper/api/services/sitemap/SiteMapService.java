package com.citytechinc.cq.harbor.proper.api.services.sitemap;

import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;
import com.citytechinc.cq.library.content.page.PageDecorator;
import org.apache.sling.api.resource.ResourceResolver;

public interface SiteMapService {

    SiteMap getSitemapEntryList(ResourceResolver resourceResolver, PageDecorator root);

}
