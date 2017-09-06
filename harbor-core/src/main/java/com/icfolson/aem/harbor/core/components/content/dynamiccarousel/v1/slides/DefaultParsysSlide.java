package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1.slides;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.ParsysSlide;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.Slide;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {ParsysSlide.class, Slide.class}, resourceType = DefaultParsysSlide.RESOURCE_TYPE)
public class DefaultParsysSlide implements ParsysSlide {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamiccarousel/v1/dynamiccarousel/slides/parsysslide";

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
