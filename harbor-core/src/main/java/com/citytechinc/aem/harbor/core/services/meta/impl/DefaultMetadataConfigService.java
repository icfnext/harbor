package com.citytechinc.aem.harbor.core.services.meta.impl;

import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.harbor.api.services.meta.MetadataConfigService;
import com.day.cq.commons.Externalizer;

@Service
@Component(label = "Page Metadata Config Service", metatype = true, immediate=true)
public class DefaultMetadataConfigService implements MetadataConfigService {
	
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetadataConfigService.class);
	
    @Property(label = "Externalizer Name", value = "publish")
    private static final String EXTERNALIZER_NAME = "externalizerName";
    private String externalizerName;
    
    @Activate
    @Modified
    protected void activate( Map<String, Object> properties ) {
        externalizerName = PropertiesUtil.toString(properties.get(EXTERNALIZER_NAME), "publish");
    }

    @Reference
    private Externalizer externalizer;

	@Override
	public String getExternalUrl(ResourceResolver resourceResolver,
			String resourcePath)  throws RepositoryException  {
		StringBuilder linkBldr = new StringBuilder();
		linkBldr.append(  this.externalizer.externalLink( resourceResolver, externalizerName,  resourceResolver.map( resourcePath) ) );
		if(isPage(resourceResolver, resourcePath)){
			linkBldr.append(".html");
		}
		return linkBldr.toString();
	}
	
    private boolean isPage(ResourceResolver resolver, String path) throws RepositoryException {
    	Resource r = resolver.getResource(path);
    	boolean isPage = false;
    	if(r!=null){
    		Node n = r.adaptTo(Node.class);
    		isPage = n.isNodeType("cq:Page");
    	}
        return isPage;
    }

}
