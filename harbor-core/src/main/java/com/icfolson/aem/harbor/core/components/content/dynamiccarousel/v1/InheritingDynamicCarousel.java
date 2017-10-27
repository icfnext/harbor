package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;


import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarousel;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicCarousel.class, resourceType = InheritingDynamicCarousel.RESOURCE_TYPE)
public class InheritingDynamicCarousel extends DefaultDynamicCarousel {

    public static final String RESOURCE_TYPE = DefaultDynamicCarousel.RESOURCE_TYPE + "/inheriting";

    @Inject
    private Resource resource;

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
