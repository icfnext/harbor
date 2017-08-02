package com.icfolson.aem.harbor.api.components.content.carousel;

import java.util.List;

public interface Carousel {

    String CSS_CLASS = "carousel slide";

    String INDICATORS_CSS_CLASS = "carousel-indicators";

    boolean isShowPreviousAndNextControls();

    boolean isShowSlideSelectorControls();

    Integer getInterval();

    boolean isPauseOnHover();

    boolean isWrap();

    boolean isKeyboard();

    List<CarouselSlide> getSlides();

    String getCssClass();

    String getIndicatorsCssClass();

}
