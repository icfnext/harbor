package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewSlideDataSource.RESOURCE_TYPE)
public class NewSlideDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewSlide.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return DynamicCarouselSlide.RESOURCE_TYPE;
    }

}
