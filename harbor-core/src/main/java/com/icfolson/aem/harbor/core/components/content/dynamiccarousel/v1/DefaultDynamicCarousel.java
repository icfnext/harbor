package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarousel;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = DynamicCarousel.class, resourceType = DefaultDynamicCarousel.RESOURCE_TYPE)
public class DefaultDynamicCarousel implements DynamicCarousel {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamiccarousel/v1/dynamiccarousel";

    @Inject
    private Resource resource;

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

    public Iterable<DynamicCarouselSlide> getSlides() {
        return Lists.newArrayList(getResource().getChildren())
                .stream()
                .map(currentChild -> currentChild.adaptTo(DynamicCarouselSlide.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getId() {
        return ComponentUtils.DomIdForResourcePath(getResource().getPath());
    }

    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

    public Resource getResource() {
        return resource;
    }

}
