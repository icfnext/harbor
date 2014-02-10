package com.citytechinc.cq.harbor.components.content.html;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

@Component( "HTML" )
public class HTML extends AbstractComponent {

    @DialogField( fieldLabel = "Source", fieldDescription = "Enter HTML markup directly.  It is recommended that you run your markup through a validator or test it outside the context of the site to ensure its validity before placing it into your content.", additionalProperties = { @FieldProperty( name = "grow", value = "{Boolean}true" ) } )
    @TextArea
    private final String HTMLSource;

    public HTML(ComponentRequest request) {
        super(request);

        HTMLSource = get("HTMLSource", StringUtils.EMPTY);
    }

    public String getHtmlSource() {
        return HTMLSource;
    }

}
