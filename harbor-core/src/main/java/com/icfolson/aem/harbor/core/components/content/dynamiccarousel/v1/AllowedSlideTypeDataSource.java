package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = AllowedSlideTypeDataSource.RESOURCE_TYPE)
public class AllowedSlideTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewSlide.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return DynamicCarouselSlide.RESOURCE_TYPE;
    }

}
