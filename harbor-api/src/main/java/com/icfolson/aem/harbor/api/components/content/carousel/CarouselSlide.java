package com.icfolson.aem.harbor.api.components.content.carousel;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;

public interface CarouselSlide extends Classifiable {

    String getDisplayIndex();

    String getPath();

    String getType();

}
