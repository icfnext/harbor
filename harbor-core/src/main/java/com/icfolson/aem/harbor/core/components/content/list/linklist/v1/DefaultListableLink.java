package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import com.icfolson.aem.library.core.constants.PathConstants;
import com.icfolson.aem.library.models.annotations.LinkInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;

import javax.inject.Inject;
import javax.inject.Named;

@Component(
    value = "Listable Link (v1)",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "lists/linklist/v1/listablelink",
    htmlTag = { @HtmlTag(tagName = "li") },
    actions = { "text: Listable Link", "-", "edit", "delete" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.LinkList.v1.LinkList.moveUp( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.LinkList.v1.LinkList.moveDown( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
    }
)
@Model(adaptables = Resource.class, adapters = ListableLink.class, resourceType = DefaultListableLink.RESOURCE_TYPE)
public class DefaultListableLink extends AbstractComponent implements ListableLink {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/linklist/v1/listablelink";

    @DialogField(fieldLabel = "Link", fieldDescription = "Either an internal page path or a full external URL", ranking = 10) @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getHref() {
        return getAsHref("href").or("#");
    }

    @DialogField(fieldLabel = "Title", ranking = 0) @TextField
    public String getTitle() {
        return IconUtils.iconify(get("title", "Listable Link Item"));
    }

    @DialogField(fieldLabel = "Open in new window", ranking = 20) @Switch
    public boolean isOpenInNewWindow() {
        return get("openInNewWindow", false);
    }

}
