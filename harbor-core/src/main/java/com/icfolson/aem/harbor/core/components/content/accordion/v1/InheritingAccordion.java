package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.icfolson.aem.harbor.api.components.content.accordion.Accordion;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class, adapters = Accordion.class, resourceType = InheritingAccordion.RESOURCE_TYPE)
public class InheritingAccordion extends DefaultAccordion {

    public static final String RESOURCE_TYPE = DefaultAccordion.RESOURCE_TYPE + "/inheriting";

    public boolean isOpenFirstItem() {
        return getInherited("openFirstItem", false);
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

    @Override
    public Resource getResource() {
        return getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

    @Override
    public List<ComponentNode> getComponentNodes() {
        return getResource().adaptTo(ComponentNode.class).getComponentNodes();
    }

}
