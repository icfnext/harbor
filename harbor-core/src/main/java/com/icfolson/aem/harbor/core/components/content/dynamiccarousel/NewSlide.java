package com.icfolson.aem.harbor.core.components.content.dynamiccarousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.Slide;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Dynamic Carousel Item", group = ".hidden", name = "dynamiccarousel/slide/new", touchFileName = NewSlide.DIALOG_FILE_NAME)
@Model(adaptables = Resource.class)
public class NewSlide {

    static final String DIALOG_FILE_NAME = "slidedialog";
    public static final String RESOURCE_TYPE = Slide.RESOURCE_TYPE + "/new";

    @Inject
    @Optional
    private String type;

    @DialogField(value = "Slide Type", name = "./sling:resourceType")
    @Selection(type = Selection.SELECT, dataSource = NewSlideDataSource.RESOURCE_TYPE)
    public String getType() {
        return type;
    }

}
