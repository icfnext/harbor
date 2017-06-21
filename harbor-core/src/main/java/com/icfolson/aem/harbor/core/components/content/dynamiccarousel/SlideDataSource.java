package com.icfolson.aem.harbor.core.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.core.components.dynamics.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewSlide.DATA_SOURCE_RESOURCE_TYPE)
public class SlideDataSource extends AbstractDynamicItemDataSource {

    @Override
    public String getItemResourceType() {
        return DynamicCarousel.SLIDE_RESOURCE_TYPE;
    }

}
