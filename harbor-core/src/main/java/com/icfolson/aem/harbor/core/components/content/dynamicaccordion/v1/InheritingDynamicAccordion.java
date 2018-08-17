package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordion;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicAccordion.class, resourceType = InheritingDynamicAccordion.RESOURCE_TYPE)
public class InheritingDynamicAccordion extends DefaultDynamicAccordion {

    @Inject @Self
    private Resource resource;

    public static final String RESOURCE_TYPE = DefaultDynamicAccordion.RESOURCE_TYPE + "/inheriting";

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
