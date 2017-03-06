package com.icfolson.aem.harbor.core.components.content.list.link;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Listable Link",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "lists/linklist/listablelink",
    htmlTag = { @HtmlTag(tagName = "li") },
    layout = "rollover",
    actions = { "text: Listable Link", "-", "edit", "delete" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.LinkList.moveUp( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.LinkList.moveDown( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
    },
    listeners = {
        @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
        @Listener(name = "afterdelete", value = "REFRESH_PAGE")
    }
)
@Model(adaptables = Resource.class)
public class ListableLink extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/linklist/listablelink";

    @DialogField(fieldLabel = "Path", ranking = 10)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getHref() {
        return getAsHref("href").or("#");
    }

    @DialogField(fieldLabel = "Title", ranking = 0)
    @TextField
    public String getTitle() {
        return IconUtils.iconify(get("title", "Link Title"));
    }
}
