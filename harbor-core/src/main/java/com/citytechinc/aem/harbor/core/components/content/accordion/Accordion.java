package com.citytechinc.aem.harbor.core.components.content.accordion;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Predicate;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
    value = "Accordion",
    group = ComponentGroups.HARBOR,
    actions = { "text: Accordion", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Item", handler = "function(){Harbor.Components.Accordion.addItem(this)}")
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

    @DialogField(fieldLabel = "Open first item",
        fieldDescription = "With this checkbox checked, the first accordion item will be open by default when not in edit or design mode.")
    @Selection(type = Selection.CHECKBOX, options = {@Option(value = "true")})
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
        boolean isAccordionItem = false;
        if (input != null && input.getResource() != null) {
            final String resourceType = input.getResource().getResourceType();
            isAccordionItem = AccordionItem.TYPE.equals(resourceType)
                || "wcm/msm/components/ghost".equals(resourceType);
        }
        return isAccordionItem;
    }
}
