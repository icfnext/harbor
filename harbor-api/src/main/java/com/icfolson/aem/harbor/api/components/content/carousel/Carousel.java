package com.icfolson.aem.harbor.api.components.content.carousel;

import java.util.List;

public interface Carousel<T extends CarouselSlide> {

    String CSS_CLASS = "carousel slide";

    String INDICATORS_CSS_CLASS = "carousel-indicators";

    boolean isShowPreviousAndNextControls();

    boolean isShowSlideSelectorControls();

    int getInterval();

    boolean isPauseOnHover();

    boolean isWrap();

    boolean isKeyboard();

    List<T> getSlides();

}
