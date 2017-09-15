package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.items.parsysaccordionitem.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {DynamicAccordionItem.class, ParagraphSystemContainer.class}, resourceType = ParsysAccordionItem.RESOURCE_TYPE)
public class ParsysAccordionItem implements DynamicAccordionItem, ParagraphSystemContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/items/parsysaccordionitem/v1/parsysaccordionitem";

    @Inject @Optional
    private String headingText;

    @Inject
    private Resource resource;

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(resource.getPath());
    }

    @DialogField(fieldLabel = "Heading Text") @TextField
    @Override
    public String getHeadingText() {
        return headingText;
    }

    @Override
    public String getType() {
        return resource.getResourceType();
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.PARSYS;
    }
}
