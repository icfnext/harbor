package com.icfolson.aem.harbor.core.components.content.responsiveimage;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.responsiveimage.ResponsiveImage;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(
    value = "Responsive Image",
    name = "responsiveimage/v1/responsiveimage",
    group = ComponentGroups.HARBOR,
    listeners = {
        @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
        @Listener(name = "afteredit", value = "REFRESH_PAGE"),
        @Listener(name = "afterdelete", value = "REFRESH_PAGE")
    }
)
@Model(adaptables = Resource.class, adapters = ResponsiveImage.class, resourceType = DefaultResponsiveImage.RESOURCE_TYPE)
public class DefaultResponsiveImage extends AbstractComponent implements ResponsiveImage {

    public static final String RESOURCE_TYPE = "harbor/components/content/responsiveimage/v1/responsiveimage";

    @DialogField(fieldLabel = "Image Widths",
        fieldDescription = "Widths to define the image source set.  Renditions will be generated for each width value.",
        ranking = 6)
    @MultiField
    @NumberField(allowDecimals = false, allowNegative = false)
    @Inject
    @Optional
    private List<Integer> imageWidths = Lists.newArrayList();

    @DialogField(ranking = 8)
    @DialogFieldSet
    @Self
    private Classification classification;

    @DialogField(fieldLabel = "Image", fieldDescription = "Image", ranking = 1)
    @Html5SmartImage(tab = false, allowUpload = false, uploadUrl = "", title = "Drag & Drop Image")
    public String getImage() {
        return getImageReference().or("");
    }

    @DialogField(fieldLabel = "Link", fieldDescription = "Optional link target for image.", ranking = 2)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getImageLink() {
        return getAsHref("imageLink").or("");
    }

    @DialogField(fieldLabel = "Shape", ranking = 3)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = Bootstrap.IMAGE_CLASS),
        @Option(text = "Rounded", value = Bootstrap.IMAGE_ROUNDED_CLASS),
        @Option(text = "Circle", value = Bootstrap.IMAGE_CIRCLE_CLASS),
        @Option(text = "Thumbnail", value = Bootstrap.IMAGE_THUMBNAIL_CLASS)
    })
    public String getShape() {
        return get("shape", Bootstrap.IMAGE_CLASS);
    }

    @DialogField(fieldLabel = "Title", ranking = 4)
    @TextField
    public String getTitle() {
        return get("title", "");
    }

    @DialogField(fieldLabel = "Alt Text", ranking = 5)
    @TextField
    public String getAlt() {
        return get("alt", "");
    }

    @DialogField(fieldLabel = "Sizes", fieldDescription = "Sizes attribute value for use with source set.", ranking = 7)
    @TextField
    public String getSizes() {
        return get("sizes", "");
    }

    public String getSourceSet() {
        return imageWidths.stream()
            .map(width -> new StringBuilder(getImageSource(width).or(""))
                .append(" ")
                .append(width)
                .append("w"))
            .collect(Collectors.joining(", "));
    }

    public String getCssClass() {
        final StringBuilder builder = new StringBuilder(getShape());

        if (classification.isHasClassifications()) {
            builder.append(" ");
            builder.append(classification.getClassNames());
        }

        return builder.toString();
    }
}
