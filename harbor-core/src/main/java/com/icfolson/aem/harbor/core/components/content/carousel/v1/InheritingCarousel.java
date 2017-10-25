package com.icfolson.aem.harbor.core.components.content.carousel.v1;

import com.icfolson.aem.harbor.api.components.content.carousel.Carousel;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = Carousel.class, resourceType = InheritingCarousel.RESOURCE_TYPE)
public class InheritingCarousel extends DefaultCarousel {

    public static final String RESOURCE_TYPE = DefaultCarousel.RESOURCE_TYPE + "/inheriting";

    @Inject
    private Resource resource;

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
