package com.icfolson.aem.harbor.core.components.content.dynamictabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.Tab;
import com.icfolson.aem.harbor.core.components.content.dynamiccarousel.NewSlide;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Dynamic Tabs Item", group = ".hidden", name = "dynamictabs/tab/new", touchFileName = NewTab.DIALOG_FILE_NAME)
@Model(adaptables = Resource.class)
public class NewTab {

    public static final String RESOURCE_TYPE = Tab.RESOURCE_TYPE + "/new";
    public static final String DIALOG_FILE_NAME = "tabdialog";

    @Inject @Optional
    private String type;

    @DialogField(value = "Tab Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewTabDataSource.RESOURCE_TYPE)
    public String getType() {
        return type;
    }

}
