package com.citytechinc.aem.harbor.api.services.meta;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.ResourceResolver;

public interface MetadataConfigService {
	
	public String getExternalUrl(ResourceResolver resourceResolver, String resourcePath)  throws RepositoryException ;

}
