package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.ExternalLinkItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.LinkItem;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(
        value = "Harbor External Link Item (v1)",
        group = ".hidden",
        resourceSuperType = DynamicListItem.RESOURCE_TYPE,
        name = "lists/dynamiclist/items/externallinkitem/v1/externallinkitem",
        actions = { "text: External Link Item", "-", "edit", "delete" },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.DynamicList.v1.DynamicList.moveUp( this )}",
                        additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
                @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.DynamicList.v1.DynamicList.moveDown( this )}",
                        additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
        },
        listeners = {
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        })
@Model(adaptables = Resource.class, adapters = {ExternalLinkItem.class, LinkItem.class}, resourceType = DefaultExternalLinkItem.RESOURCE_TYPE)
public class DefaultExternalLinkItem implements ExternalLinkItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist/items/externallinkitem/v1/externallinkitem";

    @DialogField(fieldLabel = "URL") @TextField
    @Inject @Optional
    private String url;

    @DialogField(fieldLabel = "Label") @TextField
    @Inject @Optional
    private String label;

    public String getUrl() {
        return url;
    }

    public String getLabel() {
        return IconUtils.iconify(label);
    }

}
