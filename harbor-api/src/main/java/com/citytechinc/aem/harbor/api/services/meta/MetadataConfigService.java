package com.citytechinc.aem.harbor.api.services.meta;

import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public interface MetadataConfigService {
	
	public String getExternalUrl(SlingHttpServletRequest requestContext, Resource resource, String extension);

	public String getExternalUrlForPage(SlingHttpServletRequest requestContext, Resource resource);

	public String getExternalUrlForImage(SlingHttpServletRequest requestContext, Resource resource);

}
