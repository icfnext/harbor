package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

@Component(value = "New Dynamic Accordion Item (v1)",
        group = ".hidden",
        name = "dynamicaccordion/v1/dynamicaccordion/item/new",
        touchFileName = NewAccordionItem.DIALOG_FILE_NAME)
public class NewAccordionItem {

    public static final String RESOURCE_TYPE = DefaultDynamicAccordion.RESOURCE_TYPE + "/item/new";
    public static final String DIALOG_FILE_NAME = "itemdialog";

    @DialogField(value = "Tab Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewAccordionItemDataSource.RESOURCE_TYPE)
    public String getType() {
        return "Resource Type";
    }

}
