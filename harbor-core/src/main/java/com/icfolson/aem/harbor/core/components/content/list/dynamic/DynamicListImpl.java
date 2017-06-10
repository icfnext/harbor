package com.icfolson.aem.harbor.core.components.content.list.dynamic;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicList;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.List;

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
                        handler = "function() { Harbor.Components.DynamicList.addItem( this, '" + DynamicListImpl.ADD_ITEM_DIALOG + "' ); }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        })
        }
)
@Model(adaptables = Resource.class)
public class DynamicListImpl implements DynamicList {

    static final String ADD_ITEM_DIALOG = "/apps/" + DynamicListItem.RESOURCE_TYPE + "/new/" + NewDynamicListItem.DIALOG_FILE_NAME;

    //@Inject @Self
    private List<DynamicListItem> items;

    @Self
    private Classification classification;

    public List<DynamicListItem> getItems() {
        return items;
    }

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

}
