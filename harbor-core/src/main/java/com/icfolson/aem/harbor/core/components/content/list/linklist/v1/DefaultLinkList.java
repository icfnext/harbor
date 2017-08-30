package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.components.content.list.linklist.LinkList;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Component(
    value = "Link List (v1)",
    group = ComponentGroups.HARBOR_LISTS,
    name = "lists/linklist/v1/linklist",
    isContainer = true,
    actions = { "text:Link List (v1)", "edit", "-", "copymove", "delete", "insert" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(
            text = "Add Link",
            handler = "function() { Harbor.Components.LinkList.v1.LinkList.addLink( this, '" + DefaultListableLink.RESOURCE_TYPE + "' ); }",
            additionalProperties = {
                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
            })
    }
)
@Model(adaptables = Resource.class, adapters = LinkList.class, resourceType = DefaultLinkList.RESOURCE_TYPE)
public class DefaultLinkList implements LinkList {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/linklist/v1/linklist";

    @Self
    private Classification classification;

    @DialogField @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

}
