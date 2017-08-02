package com.icfolson.aem.harbor.core.components.content.carousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Carousel Slide",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "carousel/v1/carousel/carouselslide",
    tabs = {
        @Tab(title = "Image"),
        @Tab(title = "Caption")
    },
    listeners = {
        @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
        @Listener(name = "afteredit", value = "REFRESH_PAGE")
    })
@Model(adaptables = Resource.class, adapters = CarouselSlide.class, resourceType = DefaultCarouselSlide.RESOURCE_TYPE)
public class DefaultCarouselSlide extends AbstractComponent implements CarouselSlide {

    public static final String RESOURCE_TYPE = "harbor/components/content/carousel/v1/carousel/carouselslide";

    @DialogField(fieldLabel = "Image", fieldDescription = "Image", tab = 1, ranking = 1)
    @Html5SmartImage(tab = false, allowUpload = false, uploadUrl = "", title = "Drag & Drop Image")
    public String getImage() {
        final Integer width = getWidth();

        return width == 0 ? getImageReference().or("") : getImageSource(width).or("");
    }

    @DialogField(fieldLabel = "Link", fieldDescription = "Link target for image.", tab = 1, ranking = 2)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getImageLink() {
        return getAsHref("imageLink").or("");
    }

    @DialogField(fieldLabel = "Width",
        fieldDescription = "Image width.  Defaults to 0, which will render the image at actual size.", tab = 1,
        ranking = 3)
    @NumberField(allowDecimals = false, allowNegative = false)
    public Integer getWidth() {
        return get("width", 0);
    }

    @DialogField(fieldLabel = "Alt Text", tab = 1, ranking = 4)
    @TextField
    public String getAlt() {
        return get("alt", "");
    }

    @DialogField(fieldLabel = "Title", tab = 1, ranking = 5)
    @TextField
    public String getTitle() {
        return get("title", "");
    }

    @DialogField(fieldLabel = "Caption Heading", tab = 2, ranking = 1)
    @TextField
    public String getCaptionHeading() {
        return get("captionHeading", "");
    }

    @DialogField(fieldLabel = "Caption Heading Type", tab = 2,
        fieldDescription = "The type of heading to render.  Defaults to Heading 3.")
    @Selection(type = Selection.SELECT, options = {
        @Option(text = Headings.H2_LABEL, value = Headings.H2),
        @Option(text = Headings.H3_LABEL, value = Headings.H3),
        @Option(text = Headings.H4_LABEL, value = Headings.H4),
        @Option(text = Headings.H5_LABEL, value = Headings.H5),
        @Option(text = Headings.H6_LABEL, value = Headings.H6)
    })
    public String getCaptionHeadingType() {
        return get("captionHeadingType", Headings.H3);
    }

    @DialogField(fieldLabel = "Caption Text", tab = 2, ranking = 2)
    @TextField
    public String getCaptionText() {
        return get("captionText", "");
    }

    public String getDisplayIndex() {
        return String.valueOf(getIndex() + 1);
    }
}