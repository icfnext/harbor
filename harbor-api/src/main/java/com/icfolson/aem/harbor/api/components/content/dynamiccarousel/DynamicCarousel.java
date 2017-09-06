package com.icfolson.aem.harbor.api.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface DynamicCarousel extends Identifiable, Classifiable {

    boolean isShowPreviousAndNextControls();

    boolean isShowSlideSelectorControls();

    int getInterval();

    boolean isPauseOnHover();

    boolean isWrap();

    boolean isKeyboard();

    Iterable<DynamicCarouselSlide> getSlides();

}
