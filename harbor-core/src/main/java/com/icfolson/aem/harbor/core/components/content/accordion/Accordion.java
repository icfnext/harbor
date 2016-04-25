package com.icfolson.aem.harbor.core.components.content.accordion;

import java.util.ArrayList;
import java.util.List;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.base.Predicate;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
    value = "Accordion Group",
    group = ComponentGroups.HARBOR,
    actions = { "text: Accordion", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(
                text = "Add Item",
                handler = "function(){Harbor.Components.Accordion.addItem( this, 'harbor/components/content/accordion/accordionitem' )}",
                additionalProperties = {@ActionConfigProperty(name = "icon", value = "coral-Icon--add")})
    },
    contentAdditionalProperties = {
        @ContentProperty(name = "dependencies",
            value = "[harbor.components.content.accordion,harbor.components.content.accordion.author]")
    }
)
@AutoInstantiate(instanceName = "accordion")
@Model(adaptables = Resource.class)
public class Accordion extends AbstractComponent {
    private List<AccordionItem> accordionItems;

    public String getName() {
        return this.getResource().getName();
    }

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Open First Item",
        fieldDescription = "Whether the first Accordion Item in the Accordion Group should be opened by default.")
    @Switch(offText = "No", onText = "Yes")
    public boolean isOpenFirstItem() {
        return get("openFirstItem", false);
    }

    public List<AccordionItem> getItems() {
        if (accordionItems == null) {
            accordionItems = new ArrayList<AccordionItem>();

            for (ComponentNode currentComponentNode : getComponentNodes(new AccordionItemPredicate())) {
                accordionItems.add(currentComponentNode.getResource().adaptTo(AccordionItem.class));
            }
        }
        return accordionItems;
    }

    public boolean isHasItems() {
        return !getItems().isEmpty();
    }

    public String getUniqueId() {
        return ComponentUtils.getUniqueIdentifierForResourceInPage(currentPage, getResource());
    }

}

final class AccordionItemPredicate implements Predicate<ComponentNode> {

    public boolean apply(ComponentNode input) {
        return input != null && input.getResource() != null && input.getResource().isResourceType(AccordionItem.TYPE);
    }
}
