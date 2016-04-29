package com.icfolson.aem.harbor.core.services.meta.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import com.day.cq.rewriter.linkchecker.LinkCheckerConfigProvider;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icfolson.aem.harbor.api.services.meta.MetadataConfigService;
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

	@Reference
	private LinkCheckerConfigProvider linkCheckerConfigProvider;

	@Override
	public String getExternalUrl(SlingHttpServletRequest requestContext, Resource resource, String extension) {
		String externalLink = externalizer.externalLink(
				requestContext.getResourceResolver(),
				externalizerName,
				requestContext.getResourceResolver().map(requestContext, resource.getPath()));

		if (StringUtils.isNotBlank(extension)) {
			return externalLink + "." + extension;
		}

		return externalLink;
	}

	@Override
	public String getExternalUrlForPage(SlingHttpServletRequest requestContext, Resource resource) {
		//TODO: Fix externalization - for some reason the .getConfig() call started erroring appropo of nothing
		/*
		if (linkCheckerConfigProvider.getConfig().isStripHtmlExtension()) {
			return getExternalUrl(requestContext, resource, null);
		}
		*/

		return getExternalUrl(requestContext, resource, ".html");
	}

	@Override
	public String getExternalUrlForImage(SlingHttpServletRequest requestContext, Resource resource) {
		//TODO: Check on this output
		return getExternalUrl(requestContext, resource, ".png");
	}

}
