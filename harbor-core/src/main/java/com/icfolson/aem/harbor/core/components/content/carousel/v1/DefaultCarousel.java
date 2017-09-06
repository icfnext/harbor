package com.icfolson.aem.harbor.core.components.content.carousel.v1;

import com.icfolson.aem.harbor.api.components.content.carousel.Carousel;
import com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = Carousel.class, resourceType = DefaultCarousel.RESOURCE_TYPE)
public class DefaultCarousel implements Carousel<CarouselSlide> {

    @Inject
    private ComponentNode componentNode;

    private List<CarouselSlide> slides;

    public static final String RESOURCE_TYPE = "harbor/components/content/carousel/v1/carousel";

    public static final String INDICATORS_CSS_CLASS = "carousel-indicators";

    public boolean isShowPreviousAndNextControls() {
        return true;
    }

    public boolean isShowSlideSelectorControls() {
        return true;
    }

    public int getInterval() {
        return 5000;
    }

    public boolean isPauseOnHover() {
        return true;
    }

    public boolean isWrap() {
        return true;
    }

    public boolean isKeyboard() {
        return true;
    }

    public List<CarouselSlide> getSlides() {
        if (slides == null) {
            slides = componentNode.getComponentNodes()
                    .stream()
                    .map(componentNode -> componentNode.getResource().adaptTo(CarouselSlide.class))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return slides;
    }

}
