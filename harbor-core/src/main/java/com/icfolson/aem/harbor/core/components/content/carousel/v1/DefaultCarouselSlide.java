package com.icfolson.aem.harbor.core.components.content.carousel.v1;

import com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = CarouselSlide.class, resourceType = DefaultCarouselSlide.RESOURCE_TYPE)
public class DefaultCarouselSlide extends AbstractComponent implements CarouselSlide {

    public static final String RESOURCE_TYPE = "harbor/components/content/carousel/v1/carousel/carouselslide";

    public String getDisplayIndex() {
        return String.valueOf(getIndex() + 1);
    }

    @Override
    public String getType() {
        return getResource().getResourceType();
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

}