package com.icfolson.aem.harbor.core.components.content.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Tabs",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    actions = { "text: Tabs", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    isContainer = true,
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(
            text = "Add Tab",
            handler = "function(){Harbor.Components.Tabs.addTab( this, '" + Tab.RESOURCE_TYPE + "' ) }",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
            })
    })
@Model(adaptables = Resource.class)
public class Tabs extends AbstractComponent {

    private static final Predicate<ComponentNode> TAB_PREDICATE = componentNode -> componentNode != null
        && componentNode.getResource().isResourceType(Tab.RESOURCE_TYPE);

    private List<Tab> tabs;

    public String getName() {
        return getResource().getName();
    }

    @DialogField(fieldLabel = "Tab Type", value = "tabs")
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Tabs", value = "tabs"),
        @Option(text = "Pills", value = "pills")
    })
    public String getTabType() {
        return get("tabType", Bootstrap.TAB_NAVIGATION_TYPE);
    }

    public List<Tab> getTabs() {
        if (tabs == null) {
            tabs = getComponentNodes(TAB_PREDICATE)
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(Tab.class))
                .collect(Collectors.toList());
        }

        return tabs;
    }

    public Boolean isHasTabs() {
        return !getTabs().isEmpty();
    }
}