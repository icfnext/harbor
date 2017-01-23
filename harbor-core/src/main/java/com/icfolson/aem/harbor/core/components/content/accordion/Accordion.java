package com.icfolson.aem.harbor.core.components.content.accordion;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Accordion Group",
    group = ComponentGroups.HARBOR,
    actions = { "text: Accordion", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(
            text = "Add Item",
            handler = "function(){Harbor.Components.Accordion.addItem( this, 'harbor/components/content/accordion/accordionitem' )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd") })
    },
    contentAdditionalProperties = {
        @ContentProperty(name = "dependencies",
            value = "[harbor.components.content.accordion,harbor.components.content.accordion.author]")
    }
)
@Model(adaptables = Resource.class)
public class Accordion extends AbstractComponent {

    private List<AccordionItem> accordionItems;

    public String getName() {
        return getResource().getName();
    }

    @DialogField(fieldLabel = "Open First Item",
        fieldDescription = "Whether the first Accordion Item in the Accordion Group should be opened by default.")
    @Switch(offText = "No", onText = "Yes")
    public Boolean isOpenFirstItem() {
        return get("openFirstItem", false);
    }

    public List<AccordionItem> getItems() {
        if (accordionItems == null) {
            accordionItems = getComponentNodes(new AccordionItemPredicate())
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(AccordionItem.class))
                .collect(Collectors.toList());
        }

        return accordionItems;
    }

    public Boolean isHasItems() {
        return !getItems().isEmpty();
    }
}

final class AccordionItemPredicate implements Predicate<ComponentNode> {

    public boolean apply(ComponentNode input) {
        return input != null && input.getResource() != null && input.getResource().isResourceType(
            AccordionItem.RESOURCE_TYPE);
    }
}
