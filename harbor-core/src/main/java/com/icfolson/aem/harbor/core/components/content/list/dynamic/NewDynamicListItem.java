package com.icfolson.aem.harbor.core.components.content.list.dynamic;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Dynamic List Item", group = ".hidden", name = "lists/dynamiclist/item/new", touchFileName = NewDynamicListItem.DIALOG_FILE_NAME, editConfig = false)
@Model(adaptables = Resource.class)
public class NewDynamicListItem {

    public static final String RESOURCE_TYPE = DynamicListItem.RESOURCE_TYPE + "/new";

    static final String DIALOG_FILE_NAME = "listitemdialog";

    @Inject @Optional
    private String type;

    @DialogField(value = "List Item Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewDynamicListItemDataSource.RESOURCE_TYPE)
    public String getType() {
        return type;
    }

}
