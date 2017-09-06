package com.icfolson.aem.harbor.core.components.content.list.dynamic.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

@Component(value = "Dynamic List Item (v1)", group = ".hidden", name = "lists/dynamiclist/v1/dynamiclist/item/new", touchFileName = NewDynamicListItem.DIALOG_FILE_NAME, editConfig = false)
public class NewDynamicListItem {

    public static final String RESOURCE_TYPE = DefaultDynamicList.RESOURCE_TYPE + "/item/new";

    public static final String DIALOG_FILE_NAME = "listitemdialog";

    @DialogField(value = "List Item Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewDynamicListItemDataSource.RESOURCE_TYPE)
    public String getType() {
        return "Resource Type";
    }

}
