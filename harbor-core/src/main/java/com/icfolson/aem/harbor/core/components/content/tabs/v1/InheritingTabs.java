package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.icfolson.aem.harbor.api.components.content.tabs.Tabs;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = Tabs.class, resourceType = InheritingTabs.RESOURCE_TYPE)
public class InheritingTabs extends DefaultTabs {

    public static final String RESOURCE_TYPE = DefaultTabs.RESOURCE_TYPE + "/inheriting";

    @Inject @Self
    private Resource resource;

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
