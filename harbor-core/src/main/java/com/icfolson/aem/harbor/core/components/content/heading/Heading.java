package com.icfolson.aem.harbor.core.components.content.heading;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * The Heading component is intended to be used to title content sections. The
 * only options available to the Heading from a size perspective are H2-H6 as it
 * is <em>not</em> intended to represent a page title. The Title component
 * should be used if a Page Title is intended.
 */
@Component(
    value = "Heading",
    resourceSuperType = AbstractHeading.RESOURCE_TYPE,
    tabs = {
        @Tab(title = "Heading"),
        @Tab(title = "Advanced")
    }
)
@Model(adaptables = Resource.class)
public class Heading extends AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading";

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
        return get("size", Headings.H2);
    }
}
