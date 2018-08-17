package com.icfolson.aem.harbor.core.components.content.list.dynamic.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicList;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicList.class, resourceType = InheritingDynamicList.RESOURCE_TYPE)
public class InheritingDynamicList extends DefaultDynamicList {

    public static final String RESOURCE_TYPE = DefaultDynamicList.RESOURCE_TYPE + "/inheriting";

    @Inject
    private Resource resource;

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
