package com.icfolson.aem.harbor.core.services.meta.impl;

import com.day.cq.commons.Externalizer;
import com.day.cq.rewriter.linkchecker.LinkCheckerConfigProvider;
import com.icfolson.aem.harbor.api.services.meta.MetadataConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.osgi.PropertiesUtil;

import java.util.Map;

@Service
@Component(label = "Page Metadata Config Service", metatype = true, immediate = true)
public class DefaultMetadataConfigService implements MetadataConfigService {

    @Property(label = "Externalizer Name", value = "publish")
    private static final String EXTERNALIZER_NAME = "externalizerName";

    private String externalizerName;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
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
