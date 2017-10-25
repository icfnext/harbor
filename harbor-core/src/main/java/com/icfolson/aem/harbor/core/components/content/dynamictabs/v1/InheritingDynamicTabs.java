package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTabs;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicTabs.class, resourceType = InheritingDynamicTabs.RESOURCE_TYPE)
public class InheritingDynamicTabs extends DefaultDynamicTabs {

    @Inject
    private Resource resource;

    public static final String RESOURCE_TYPE = DefaultDynamicTabs.RESOURCE_TYPE + "/inheriting";

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
