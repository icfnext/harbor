package com.icfolson.aem.harbor.core.components.content.dynamicaccordion;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.AccordionItem;
import com.icfolson.aem.harbor.core.components.content.dynamictabs.NewTabDataSource;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "New Dynamic Accordion Item", group = ".hidden", name = "dynamicaccordion/item/new", touchFileName = NewAccordionItem.DIALOG_FILE_NAME)
@Model(adaptables = Resource.class)
public class NewAccordionItem {

    public static final String RESOURCE_TYPE = AccordionItem.RESOURCE_TYPE + "/new";
    public static final String DIALOG_FILE_NAME = "itemdialog";

    @Inject
    @Optional
    private String type;

    @DialogField(value = "Tab Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewAccordionItemDataSource.RESOURCE_TYPE)
    public String getType() {
        return type;
    }

}
