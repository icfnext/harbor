package com.icfolson.aem.harbor.core.components.content.carousel;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Carousel Slide",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "carousel/carouselslide",
    actions = { "text: Carousel Slide", "-", "delete" })
@AutoInstantiate
@Model(adaptables = Resource.class)
public class CarouselSlide extends AbstractComponent {

}