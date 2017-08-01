package com.icfolson.aem.harbor.core.components.content.list.dynamic;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Component(
        value = "Dynamic List",
        group = ComponentGroups.HARBOR_LISTS,
        name = "lists/dynamiclist",
        isContainer = true,
        actions = { "text:Dynamic List", "edit", "-", "copymove", "delete", "insert" },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(
                        text = "Add Item",
                        handler = "function() { Harbor.Components.DynamicList.addItem( this, '/apps/" + NewDynamicListItem.RESOURCE_TYPE + "/" + NewDynamicListItem.DIALOG_FILE_NAME + "' ); }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        })
        }
)
@Model(adaptables = Resource.class)
public class DynamicList {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist";

    @Self
    private Classification classification;

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

}
