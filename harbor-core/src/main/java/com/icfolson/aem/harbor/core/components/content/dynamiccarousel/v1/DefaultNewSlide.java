package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.NewSlide;
import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.Slide;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(value = "Dynamic Carousel Item", group = ".hidden", name = "dynamiccarousel/v1/dynamiccarousel/slide/new", touchFileName = DefaultNewSlide.DIALOG_FILE_NAME)
@Model(adaptables = Resource.class, adapters = NewSlide.class, resourceType = DefaultNewSlide.RESOURCE_TYPE)
public class DefaultNewSlide implements NewSlide {

    public static final String DIALOG_FILE_NAME = "slidedialog";
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
