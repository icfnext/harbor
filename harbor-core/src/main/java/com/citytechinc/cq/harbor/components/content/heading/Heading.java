package com.citytechinc.cq.harbor.components.content.heading;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.dom.Headings;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

/**
 * The Heading component is intended to be used to title content sections.  The only options available to
 * the Heading from a size perspective are H2-H6 as it is <em>not</em> intended to represent a page title.
 * The Title component should be used if a Page Title is intended.
 */
@Component("Heading")
public class Heading extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading";

    public static final String SIZE_PROPERTY = "size";
    public static final String TEXT_PROPERTY = "text";

    public Heading(ComponentRequest request) {
        super(request);
    }

    @DialogField( fieldLabel = "Heading Type", fieldDescription = "The type or size of heading to render." )
    @Selection( type = "select",
        options = {
            @Option( text = Headings.H2_LABEL, value = Headings.H2 ),
            @Option( text = Headings.H3_LABEL, value = Headings.H3 ),
            @Option( text = Headings.H4_LABEL, value = Headings.H4 ),
            @Option( text = Headings.H5_LABEL, value = Headings.H5 ),
            @Option( text = Headings.H6_LABEL, value = Headings.H6 )
    } )
    public String getSize() {
        return get(SIZE_PROPERTY, Headings.H2);
    }

    @DialogField( fieldLabel = "Heading Text", fieldDescription = "The textual content of the rendered heading." )
    public String getText() {
        return get(TEXT_PROPERTY, StringUtils.EMPTY);
    }

}
