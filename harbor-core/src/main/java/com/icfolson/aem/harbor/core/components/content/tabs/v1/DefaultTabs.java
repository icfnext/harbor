package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.tabs.Tab;
import com.icfolson.aem.harbor.api.components.content.tabs.Tabs;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Component(
    value = "Tabs (v1)",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    actions = { "text: Tabs (v1)", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    isContainer = true,
    name = "tabs/v1/tabs",
    actionConfigs = {
            @ActionConfig(xtype = "tbseparator"),
            @ActionConfig(
                text = "Add Tab",
                handler = "function(){Harbor.Components.Tabs.v1.Tabs.addTab( this, '" + DefaultTab.RESOURCE_TYPE + "' ) }",
                additionalProperties = {
                    @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                }),
            @ActionConfig(text = "Previous Tab",
                    handler = "function() { Harbor.Components.Tabs.v1.Tabs.previousTab( this ) }",
                    additionalProperties = {
                            @ActionConfigProperty(name = "icon", value = "coral-Icon--rewindCircle")
                    } ),
            @ActionConfig(text = "Next Tab",
                    handler = "function() { Harbor.Components.Tabs.v1.Tabs.nextTab( this ) }",
                    additionalProperties = {
                            @ActionConfigProperty(name = "icon", value = "coral-Icon--fastForwardCircle")
                    } )
    })
@Model(adaptables = Resource.class, adapters = Tabs.class, resourceType = DefaultTabs.RESOURCE_TYPE)
public class DefaultTabs implements Tabs {

    public static final String RESOURCE_TYPE = "harbor/components/content/tabs/v1/tabs";

    @Inject @Self
    private Resource resource;

    private List<Tab> tabs;

    public List<Tab> getTabs() {
        if (tabs == null) {
            tabs = Lists.newArrayList();
            resource.getChildren().forEach(tabResource -> {
                Tab currentTab = tabResource.adaptTo(Tab.class);
                if (currentTab != null) {
                    tabs.add(currentTab);
                }
            });
        }

        return tabs;
    }

}