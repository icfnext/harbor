package com.icfolson.aem.harbor.core.components.content.carousel;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Carousel Slide",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "carousel/carouselslide",
    listeners = {
        @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
        @Listener(name = "afteredit", value = "REFRESH_PAGE")
    })
@Model(adaptables = Resource.class)
public class CarouselSlide extends AbstractComponent {

    @DialogField(fieldLabel = "Caption Heading", ranking = 1)
    @TextField
    public String getCaptionHeading() {
        return get("captionHeading", "");
    }

    @DialogField(fieldLabel = "Caption Heading Type",
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

    @DialogField(fieldLabel = "Caption Text", ranking = 2)
    @TextField
    public String getCaptionText() {
        return get("captionText", "");
    }

    public String getDisplayIndex() {
        return String.valueOf(getIndex() + 1);
    }
}