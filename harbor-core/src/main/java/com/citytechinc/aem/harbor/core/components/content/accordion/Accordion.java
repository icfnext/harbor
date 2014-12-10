package com.citytechinc.aem.harbor.core.components.content.accordion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;

@Component(
    value = "Accordion",
    group = ComponentGroups.HARBOR,
    isContainer = true,
    actions = { "text: Accordion", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Item", handler = "function(){Harbor.Components.Accordion.addItem(this)}")
    },
    contentAdditionalProperties = {
        @ContentProperty(name = "dependencies", value = "[harbor.components.content.accordion]")
    }
)
@AutoInstantiate(instanceName = "accordion")
public class Accordion extends AbstractComponent {
    private List<AccordionItem> accordionItems;

    public String getName() {
        return this.getResource().getName();
    }

    @DialogField(fieldLabel = "Open first item",
        fieldDescription = "With this checkbox checked, the first accordion item will be open by default.")
    @CheckBox(checked = false, inputValue = "true")
    public boolean isOpenFirstItem() {
        return get("openFirstItem", false);
    }

    public List<AccordionItem> getItems() {
        accordionItems = new ArrayList<AccordionItem>();
        final Iterator<ComponentNode> componentNodeIterator = getComponentNodes().iterator();
        while (componentNodeIterator.hasNext()) {
            final ComponentNode currentComponentNode = componentNodeIterator.next();

            if (currentComponentNode.getResource().isResourceType(AccordionItem.TYPE)) {
                accordionItems.add(getComponent(currentComponentNode, AccordionItem.class));
            }
        }
        return accordionItems;
    }

    public Boolean getHasItems() {
        return !getItems().isEmpty();
    }

    public String getUniqueId() {
        return ComponentUtils.getUniqueIdentifierForResourceInPage(getCurrentPage(), getResource());
    }

}
