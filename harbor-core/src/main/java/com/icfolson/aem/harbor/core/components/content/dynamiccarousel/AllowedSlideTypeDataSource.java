package com.icfolson.aem.harbor.core.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.core.components.content.list.dynamic.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewSlide.DESIGN_DATA_SOURCE_RESOURCE_TYPE)
public class AllowedSlideTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    @Override
    public String getItemResourceType() {
        return DynamicCarousel.SLIDE_RESOURCE_TYPE;
    }

}
