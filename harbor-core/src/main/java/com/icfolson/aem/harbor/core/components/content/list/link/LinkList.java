package com.icfolson.aem.harbor.core.components.content.list.link;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Component(
    value = "Link List",
    group = ComponentGroups.HARBOR_LISTS,
    name = "lists/linklist",
    actions = { "text:Link List", "edit", "-", "copymove", "delete", "insert" },
    contentAdditionalProperties = {
        @ContentProperty(name = "dependencies", value = "[harbor.components.content.linklist,acs-commons.fontawesome]")
    },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(
            text = "Add Link",
            handler = "function() { Harbor.Components.LinkList.addLink( this, '" + ListableLink.RESOURCE_TYPE + "' ); }",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
            })
    },
    listeners = {
        @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
        @Listener(name = "afteredit", value = "REFRESH_PAGE"),
        @Listener(name = "afterdelete", value = "REFRESH_PAGE")
    }
)
@Model(adaptables = Resource.class)
public class LinkList extends AbstractComponent {

    @Self
    private Classification classification;

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }
}
