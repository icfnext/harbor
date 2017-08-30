package com.icfolson.aem.harbor.api.components.content.dynamiccarousel;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import org.apache.sling.api.resource.Resource;

public interface DynamicCarousel {

    String CSS_CLASS = "carousel slide";

    String INDICATORS_CSS_CLASS = "carousel-indicators";

    boolean isShowPreviousAndNextControls();

    boolean isShowSlideSelectorControls();

    Integer getInterval();

    boolean isPauseOnHover();

    boolean isWrap();

    boolean isKeyboard();

    Iterable<Resource> getSlides();

    String getCssClass();

    String getIndicatorsCssClass();

    String getId();

    Classification getClassification();

}
