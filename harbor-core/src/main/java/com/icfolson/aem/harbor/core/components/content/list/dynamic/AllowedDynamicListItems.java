package com.icfolson.aem.harbor.core.components.content.list.dynamic;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

import java.util.List;

@Component(value = "Allowed Dynamic List Items", group = ".hidden", editConfig = false, touchFileName = "_cq_design_dialog")
public class AllowedDynamicListItems {

    static final String DATA_SOURCE_RESOURCE_TYPE = NewDynamicListItem.RESOURCE_TYPE + "/new/allowedoptions";

    @DialogField(fieldLabel = "Allowed Types", name = "./" + DynamicListImpl.ALLOWED_LIST_ITEMS) @MultiField @Selection(type = Selection.SELECT, dataSource = AllowedDynamicListItems.DATA_SOURCE_RESOURCE_TYPE)
    private List<String> allowedTypes;

    public List<String> getAllowedTypes() {
        return allowedTypes;
    }

}
