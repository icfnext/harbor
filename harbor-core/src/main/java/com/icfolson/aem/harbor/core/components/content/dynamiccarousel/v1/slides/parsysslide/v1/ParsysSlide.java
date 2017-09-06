package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1.slides.parsysslide.v1;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicCarouselSlide.class, resourceType = ParsysSlide.RESOURCE_TYPE)
public class ParsysSlide implements DynamicCarouselSlide {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamiccarousel/slides/parsysslide/v1/parsysslide";

    @Inject @Self
    private Resource resource;

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public String getType() {
        return resource.getResourceType();
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

}
