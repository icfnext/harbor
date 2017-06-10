package com.icfolson.aem.harbor.core.components.content.list.dynamic;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Dynamic List Item", group = ".hidden", name = "lists/dynamiclist/item/new", touchFileName = "listitemdialog")
@Model(adaptables = Resource.class)
public class NewDynamicListItem implements DynamicListItem {

    static final String DIALOG_FILE_NAME = "listitemdialog";
    static final String DATA_SOURCE_RESOURCE_TYPE = RESOURCE_TYPE + "/new/options";

    @Inject @Optional
    private String type;

    @DialogField(value = "List Item Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewDynamicListItem.DATA_SOURCE_RESOURCE_TYPE)
    public String getType() {
        return type;
    }

}
