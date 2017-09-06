package com.icfolson.aem.harbor.api.components.content.carousel;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

import java.util.List;

public interface Carousel<T extends CarouselSlide> extends Classifiable, Identifiable {

    boolean isShowPreviousAndNextControls();

    boolean isShowSlideSelectorControls();

    int getInterval();

    boolean isPauseOnHover();

    boolean isWrap();

    boolean isKeyboard();

    List<T> getSlides();

}
