package com.icfolson.aem.harbor.core.components.content.carousel.v1;

import com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide;
import com.icfolson.aem.library.api.node.BasicNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = CarouselSlide.class, resourceType = InheritingCarouselSlide.RESOURCE_TYPE)
public class InheritingCarouselSlide extends DefaultCarouselSlide {

    public static final String RESOURCE_TYPE = DefaultCarouselSlide.RESOURCE_TYPE + "/inheriting";

    public Resource getResource() {
        return getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
