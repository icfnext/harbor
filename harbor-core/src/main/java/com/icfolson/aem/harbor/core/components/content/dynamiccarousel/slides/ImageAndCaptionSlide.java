package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.slides;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.Slide;
import com.icfolson.aem.harbor.core.components.content.dynamiccarousel.DynamicCarousel;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Image and Caption Slide",
        group = ".hidden",
        resourceSuperType = Slide.RESOURCE_TYPE,
        name = "dynamiccarousel/slides/imageandcaptionslide")
@Model(adaptables = Resource.class)
public class ImageAndCaptionSlide {

    @Inject @Self
    private Classification classification;

    @DialogField @DialogFieldSet
    public Classification getClassification() {
        return this.classification;
    }

}
