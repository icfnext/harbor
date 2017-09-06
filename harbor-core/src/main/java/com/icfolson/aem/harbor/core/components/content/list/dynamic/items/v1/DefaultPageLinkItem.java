package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.LinkItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.PageLinkItem;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(
        value = "Harbor Page Link Item (v1)",
        group = ".hidden",
        resourceSuperType = DynamicListItem.RESOURCE_TYPE,
        name = "lists/dynamiclist/items/pagelinkitem/v1/pagelinkitem",
        actions = { "text: Page Link Item", "-", "edit", "delete" },
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
@Model(adaptables = Resource.class, adapters = {PageLinkItem.class, LinkItem.class}, resourceType = DefaultPageLinkItem.RESOURCE_TYPE)
public class DefaultPageLinkItem implements PageLinkItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist/items/pagelinkitem/v1/pagelinkitem";

    @DialogField(fieldLabel = "Linked Page") @PathField
    @Inject @Optional
    private PageDecorator linkedPage;

    @DialogField(fieldLabel = "Label") @TextField
    @Inject @Optional
    private String label;

    public String getUrl() {
        return linkedPage.getHref();
    }

    public String getLabel() {
        if (StringUtils.isNotBlank(label)) {
            return label;
        }

        if (linkedPage != null) {
            if (StringUtils.isNotBlank(linkedPage.getPageTitle())) {
                return linkedPage.getPageTitle();
            }

            return linkedPage.getTitle();
        }

        return StringUtils.EMPTY;
    }

}
