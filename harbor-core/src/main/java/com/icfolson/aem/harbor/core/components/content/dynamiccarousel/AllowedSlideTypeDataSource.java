package com.icfolson.aem.harbor.core.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.Slide;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = AllowedSlideTypeDataSource.RESOURCE_TYPE)
public class AllowedSlideTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewSlide.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return Slide.RESOURCE_TYPE;
    }

}
