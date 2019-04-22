package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractAllowedDynamicTypesDataSource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {
    ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + AllowedSlideTypeDataSource.RESOURCE_TYPE
})
public class AllowedSlideTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewSlide.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return DynamicCarouselSlide.RESOURCE_TYPE;
    }

}
