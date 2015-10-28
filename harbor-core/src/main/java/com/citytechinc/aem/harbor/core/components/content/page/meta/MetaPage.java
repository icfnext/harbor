package com.citytechinc.aem.harbor.core.components.content.page.meta;

import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage;
import com.citytechinc.aem.harbor.api.content.page.HomePage;
import com.citytechinc.aem.harbor.api.services.meta.MetadataConfigService;
import com.day.cq.commons.jcr.JcrConstants;


public class MetaPage extends AbstractComponent {
    private static final Logger LOG = LoggerFactory.getLogger(MetaPage.class);
    
    public boolean getDisableSchemaOrg() {
    	return getCurrentPage().getProperties().get("disableSchemaOrg", false);
    }
    
    public String getPageName(){
    	if(StringUtils.isNotBlank(getCurrentPage().getPageTitle())){
    		return getCurrentPage().getPageTitle();
    	}else{
    		return getCurrentPage().getName();
    	}
    }
    
    public String getDescription(){
    	return getCurrentPage().getProperties().get(JcrConstants.JCR_DESCRIPTION, StringUtils.EMPTY);
    }
    
    public String getFullyQualifiedPageImage() {
    	String temp = StringUtils.EMPTY;
    	MetadataConfigService metadataConfigService = getService(MetadataConfigService.class);
    	try{
    		ResourceResolver resolver = getComponentRequest().getSlingRequest().getResourceResolver();
    		Resource imageResource = getCurrentPage().getContentResource().getChild("image");
    		if(imageResource != null){
    			String imageFileRef = imageResource.getValueMap().get("fileReference",StringUtils.EMPTY);       		
    			if(StringUtils.isNotBlank(imageFileRef)) {
    				temp = metadataConfigService.getExternalUrl(resolver, imageFileRef);
    			}
    		}

    	}catch( RepositoryException re){
    		LOG.error("RepositoryException retreiving fully qualified page image string",re);
    	}
    	return temp;
    }
    
    public String getFullyQualifiedPageUrl() {
    	String temp = StringUtils.EMPTY;
    	MetadataConfigService metadataConfigService = getService(MetadataConfigService.class);
    	try{
    		ResourceResolver resolver = getComponentRequest().getSlingRequest().getResourceResolver();
    		temp = metadataConfigService.getExternalUrl(resolver, getCurrentPage().getPath());
    	}catch( RepositoryException re){
    		LOG.error("RepositoryException retreiving fully qualified page string",re);
    	}
    	return temp;
    }
    
    public String getPublisherHandle(){
    	return getCurrentPage().getProperties().get("twitterPublisherHandle", StringUtils.EMPTY);
    }
    
    public String getOgType(){
    	return getCurrentPage().getProperties().get("ogType", StringUtils.EMPTY);
    }
    
    public String getCannonicalRef(){
    	String tempCannonical = StringUtils.EMPTY;
    	String inputCannonicalRef = getCurrentPage().getProperties().get("cannonicalUrl", StringUtils.EMPTY);
    	//If they input a remote url, then just return that URL
    	if(inputCannonicalRef.startsWith("http") || inputCannonicalRef.isEmpty()){
    		return inputCannonicalRef;
    	}else{
    		//If they select a path with the pathfinder, need to externalize it.
    		try{
	        	MetadataConfigService metadataConfigService = getService(MetadataConfigService.class);
	        	ResourceResolver resolver = getComponentRequest().getSlingRequest().getResourceResolver();
	        	tempCannonical =  metadataConfigService.getExternalUrl(resolver, inputCannonicalRef);
    		}catch( RepositoryException re){
        		LOG.error("RepositoryException retreiving fully qualified page string",re);
        	}
    	}
    	
    	return tempCannonical;
    }
    
    public String getHomePageTitle(){
    	String temp = StringUtils.EMPTY;
    	HierarchicalPage hPage = getCurrentPage().adaptTo(HierarchicalPage.class);
    	if(hPage!=null){
        	HomePage homePage = hPage.getHomePage().get();
        	if(StringUtils.isNotEmpty(homePage.getPageTitle() )){
        		temp = homePage.getPageTitle();
        	}else{
        		temp = homePage.getName();
        	}
    	}
    	return temp;
    }
    
    public String getRobotsContent() {
    	StringBuilder content = new StringBuilder();
    	boolean noIndexIndicator = getCurrentPage().getProperties().get("noindex", false);
    	boolean noFollowIndicator = getCurrentPage().getProperties().get("nofollow", false);
    	
    	if(noIndexIndicator){
    		content.append("NOINDEX");
    		if(noFollowIndicator){
    			content.append(", NOFOLLOW");
    		}else{
    			content.append(", FOLLOW");
    		}
    	}else{
    		if(noFollowIndicator){
    			content.append("INDEX, NOFOLLOW");
    		}
    	}
    	
    	return content.toString();
    }
}
