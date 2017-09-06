package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

@Component(value = "Dynamic Carousel Item",
        group = ".hidden",
        name = "dynamiccarousel/v1/dynamiccarousel/slide/new",
        editConfig = false,
        touchFileName = NewSlide.DIALOG_FILE_NAME)
public class NewSlide {

    public static final String DIALOG_FILE_NAME = "slidedialog";
    public static final String RESOURCE_TYPE = DefaultDynamicCarousel.RESOURCE_TYPE + "/slide/new";

    @DialogField(value = "Slide Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewSlideDataSource.RESOURCE_TYPE)
    public String getType() {
        return "Resource Type";
    }

}
