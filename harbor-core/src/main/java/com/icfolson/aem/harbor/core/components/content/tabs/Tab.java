package com.icfolson.aem.harbor.core.components.content.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Tab",
    name = "tabs/tab",
    actions = { "text: Tab", "-", "edit", "delete" },
    isContainer = true,
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.Tabs.moveUp( this )}",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp")
            }),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.Tabs.moveDown( this )}",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown")
            })
    },
    listeners = {
        @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        @Listener(name = "afterdelete", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN
)
@Model(adaptables = Resource.class)
public class Tab extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/tabs/tab";

    @DialogField(fieldLabel = "Title", fieldDescription = "The title to be presented within the Tab")
    @TextField
    public String getTitle() {
        return IconUtils.iconify(get("title", "Tab " + getNumber()));
    }

    public Integer getNumber() {
        return getIndex() + 1;
    }

    public String getName() {
        return getResource().getName();
    }

    public boolean isGhost() {
        return !getResource().getResourceType().equals(RESOURCE_TYPE);
    }
}