package com.icfolson.aem.harbor.core.components.content.responsiveimage.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class ImageSource extends AbstractComponent {

    @DialogField(fieldLabel = "Image", fieldDescription = "Image", ranking = 1)
    @Html5SmartImage(tab = false, allowUpload = false, uploadUrl = "", title = "Drag & Drop Image")
    public String getImage() {
        return getImageReference().or("");
    }

    @DialogField(fieldLabel = "Width",
        fieldDescription = "Image width.  Defaults to 0, which will render the image at actual size.", ranking = 2)
    @NumberField(allowDecimals = false, allowNegative = false)
    public Integer getWidth() {
        return get("width", 0);
    }
}
