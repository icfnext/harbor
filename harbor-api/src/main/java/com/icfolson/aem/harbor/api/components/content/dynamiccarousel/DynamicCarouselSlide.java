package com.icfolson.aem.harbor.api.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;

public interface DynamicCarouselSlide extends Classifiable {

    String RESOURCE_TYPE = "harbor/components/content/dynamiccarousel/slide";

    String getType();

    String getPath();

    String getName();

}
