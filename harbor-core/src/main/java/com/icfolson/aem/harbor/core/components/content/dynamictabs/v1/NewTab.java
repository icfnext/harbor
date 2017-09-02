package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

@Component(value = "New Dynamic Tabs Item",
        group = ".hidden",
        name = "dynamictabs/v1/dynamictabs/tab/new",
        touchFileName = NewTab.DIALOG_FILE_NAME)
public class NewTab {

    public static final String RESOURCE_TYPE = DefaultDynamicTabs.RESOURCE_TYPE + "/tab/new";
    public static final String DIALOG_FILE_NAME = "tabdialog";

    @DialogField(value = "Tab Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewTabDataSource.RESOURCE_TYPE)
    public String getType() {
        return "Resource Type";
    }

}
