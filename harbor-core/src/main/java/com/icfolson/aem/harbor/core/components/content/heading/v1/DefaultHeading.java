package com.icfolson.aem.harbor.core.components.content.heading.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * The Heading component is intended to be used to title content sections. The
 * only options available to the Heading from a size perspective are H2-H6 as it
 * is <em>not</em> intended to represent a page title. The Title component
 * should be used if a Page Title is intended.
 */
@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = DefaultHeading.RESOURCE_TYPE)
public class DefaultHeading extends AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading/v1/heading";

    private static final String DEFAULT_TEXT = "Heading Text";

    @Inject @Default(values = DEFAULT_TEXT)
    private String text;

    @Inject @Default(values = Headings.H2)
    private String size;

    @DialogField(fieldLabel = "Text", fieldDescription = "The textual content of the rendered heading.") @TextField
    public String getText() {
        return text;
    }

    @DialogField(fieldLabel = "Heading Type", fieldDescription = "The type or size of heading to render.")
    @Selection(type = "select", options = {
        @Option(text = Headings.H2_LABEL, value = Headings.H2),
        @Option(text = Headings.H3_LABEL, value = Headings.H3),
        @Option(text = Headings.H4_LABEL, value = Headings.H4),
        @Option(text = Headings.H5_LABEL, value = Headings.H5),
        @Option(text = Headings.H6_LABEL, value = Headings.H6)
    })
    @Override
    public String getSize() {
        return size;
    }

}
