package com.citytechinc.aem.harbor.core.components.content.carousel;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Carousel Slide",
    group = ".hidden",
    name = "carousel/carouselslide",
    actions = { "text: Slide", "-", "delete" } )
@AutoInstantiate(instanceName = "carouselslide")
@Model(adaptables = Resource.class)
public class CarouselSlide extends AbstractComponent {
}
