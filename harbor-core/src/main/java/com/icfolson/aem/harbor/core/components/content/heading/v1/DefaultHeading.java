package com.icfolson.aem.harbor.core.components.content.heading.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

/**
 * The Heading component is intended to be used to title content sections. The
 * only options available to the Heading from a size perspective are H2-H6 as it
 * is <em>not</em> intended to represent a page title. The Title component
 * should be used if a Page Title is intended.
 */
@Component(
    value = "Heading (v1)",
    resourceSuperType = AbstractHeading.RESOURCE_TYPE,
    name = "heading/v1/heading",
    tabs = {
        @Tab(title = "Heading"),
        @Tab(title = "Advanced")
    }
)
@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = DefaultHeading.RESOURCE_TYPE)
public class DefaultHeading implements Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading/v1/heading";

    private static final String DEFAULT_TEXT = "Heading Text";

    @Inject @Default(values = DEFAULT_TEXT)
    private String text;

    @Inject @Default(values = Headings.H2)
    private String size;

    @Inject @Optional
    private String domId;

    @DialogField(fieldLabel = "Text", fieldDescription = "The textual content of the rendered heading.") @TextField
    public String getText() {
        return IconUtils.iconify(text);
    }

    @DialogField(fieldLabel = "ID",
            fieldDescription = "A unique identifier to apply to the Heading element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.",
            tab = 2)
    @TextField
    public String getDomId() {
        return StringUtils.isNotBlank(domId) ? domId : ComponentUtils.sanitizeTextAsDomId(text);
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
