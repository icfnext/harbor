package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.items.parsysaccordionitem.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = DynamicAccordionItem.class, resourceType = InheritingParsysAccordionItem.RESOURCE_TYPE)
public class InheritingParsysAccordionItem extends ParsysAccordionItem {

    public static final String RESOURCE_TYPE = ParsysAccordionItem.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String headingText;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Heading Text") @TextField
    @Override
    public String getHeadingText() {
        return headingText;
    }

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }


}
