package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.icfolson.aem.harbor.api.components.content.accordion.Accordion;
import com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component(
    value = "Accordion Group (v1)",
    group = ComponentGroups.HARBOR,
    actions = { "text: Accordion", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    isContainer = true,
    actionConfigs = {
        @ActionConfig(
            text = "Add Item",
            handler = "function(){Harbor.Components.Accordion.v1.Accordion.addItem( this, '" + DefaultAccordionItem.RESOURCE_TYPE + "' )}",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
            })
    }
)
@Model(adaptables = Resource.class, adapters = Accordion.class, resourceType = DefaultAccordion.RESOURCE_TYPE)
public class DefaultAccordion extends AbstractComponent implements Accordion {

    public static final String RESOURCE_TYPE = "harbor/components/content/accordion/v1/accordion";

    private List<AccordionItem> accordionItems;

    public String getName() {
        return getResource().getName();
    }

    public boolean isOpenFirstItem() {
        return get("openFirstItem", false);
    }

    public List<AccordionItem> getItems() {
        if (accordionItems == null) {
            accordionItems = getComponentNodes()
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(AccordionItem.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }

        return accordionItems;
    }

    public boolean isHasItems() {
        return !getItems().isEmpty();
    }
}