package com.icfolson.aem.harbor.api.services.meta;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

public interface MetadataConfigService {

    String getExternalUrl(SlingHttpServletRequest requestContext, Resource resource, String extension);

    String getExternalUrlForPage(SlingHttpServletRequest requestContext, Resource resource);

    String getExternalUrlForImage(SlingHttpServletRequest requestContext, Resource resource);

}
