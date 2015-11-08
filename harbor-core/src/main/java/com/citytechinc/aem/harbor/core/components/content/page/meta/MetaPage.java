package com.citytechinc.aem.harbor.core.components.content.page.meta;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage;
import com.citytechinc.aem.harbor.api.content.page.HomePage;
import com.citytechinc.aem.harbor.api.services.meta.MetadataConfigService;
import com.day.cq.commons.jcr.JcrConstants;

@Model(adaptables = Resource.class)
public class MetaPage extends AbstractComponent {
    private static final Logger LOG = LoggerFactory.getLogger(MetaPage.class);

	@Inject
	private PageDecorator currentPage;

	@Inject
	private MetadataConfigService metadataConfigService;
    
    public boolean getDisableSchemaOrg() {
    	return currentPage.getProperties().get("disableSchemaOrg", false);
    }
    
    public String getPageName(){
    	if(StringUtils.isNotBlank(currentPage.getPageTitle())){
    		return currentPage.getPageTitle();
    	}else{
    		return currentPage.getName();
    	}
    }
    
    public String getDescription(){
    	return currentPage.getProperties().get(JcrConstants.JCR_DESCRIPTION, StringUtils.EMPTY);
    }
    
    public String getFullyQualifiedPageImage() {
    	String temp = StringUtils.EMPTY;
    	try{
    		Resource imageResource = currentPage.getContentResource().getChild("image");
    		if(imageResource != null){
    			String imageFileRef = imageResource.getValueMap().get("fileReference",StringUtils.EMPTY);       		
    			if(StringUtils.isNotBlank(imageFileRef)) {
    				temp = metadataConfigService.getExternalUrl(getResource().getResourceResolver(), imageFileRef);
    			}
    		}

    	}catch( RepositoryException re){
    		LOG.error("RepositoryException retreiving fully qualified page image string",re);
    	}
    	return temp;
    }
    
    public String getFullyQualifiedPageUrl() {
    	String temp = StringUtils.EMPTY;
    	try{
    		temp = metadataConfigService.getExternalUrl(getResource().getResourceResolver(), currentPage.getPath());
    	}catch( RepositoryException re){
    		LOG.error("RepositoryException retreiving fully qualified page string",re);
    	}
    	return temp;
    }
    
    public String getPublisherHandle(){
    	return currentPage.getProperties().get("twitterPublisherHandle", StringUtils.EMPTY);
    }
    
    public String getOgType(){
    	return currentPage.getProperties().get("ogType", StringUtils.EMPTY);
    }
    
    public String getCannonicalRef(){
    	String tempCannonical = StringUtils.EMPTY;
    	String inputCannonicalRef = currentPage.getProperties().get("cannonicalUrl", StringUtils.EMPTY);
    	//If they input a remote url, then just return that URL
    	if(inputCannonicalRef.startsWith("http") || inputCannonicalRef.isEmpty()){
    		return inputCannonicalRef;
    	}else{
    		//If they select a path with the pathfinder, need to externalize it.
    		try{
	        	tempCannonical =  metadataConfigService.getExternalUrl(getResource().getResourceResolver(), inputCannonicalRef);
    		}catch( RepositoryException re){
        		LOG.error("RepositoryException retreiving fully qualified page string",re);
        	}
    	}
    	
    	return tempCannonical;
    }
    
    public String getHomePageTitle(){
    	String temp = StringUtils.EMPTY;
    	HierarchicalPage hPage = currentPage.adaptTo(HierarchicalPage.class);
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
    	boolean noIndexIndicator = currentPage.getProperties().get("noindex", false);
    	boolean noFollowIndicator = currentPage.getProperties().get("nofollow", false);
    	
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
