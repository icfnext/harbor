package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.items;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.AccordionItem;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Parsys Accordion Item",
        group = ".hidden",
        resourceSuperType = AccordionItem.RESOURCE_TYPE,
        actions = { "text: Parsys Accordion Item", "edit", "-", "delete" },
        name = "dynamicaccordion/items/parsysaccordionitem",
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        })
@Model(adaptables = Resource.class, adapters = AccordionItem.class, resourceType = ParsysAccordionItem.RESOURCE_TYPE)
public class ParsysAccordionItem implements AccordionItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/items/parsysaccordionitem";

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

    @DialogField(ranking = 20) @DialogFieldSet(border = false)
    public Classification getClassification() {
        return resource.adaptTo(Classification.class);
    }

}
