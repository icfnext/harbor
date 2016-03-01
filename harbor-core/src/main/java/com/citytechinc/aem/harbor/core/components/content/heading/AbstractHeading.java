package com.citytechinc.aem.harbor.core.components.content.heading;

import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

public abstract class AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/abstractheading";

    public static final String INSTANCE_NAME = "heading";

    @DialogField(fieldLabel = "Text", fieldDescription = "The textual content of the rendered heading.")
    @TextField
    @Inject @Optional
    protected String text;

    @DialogField(fieldLabel = "ID", fieldDescription = "A unique identifier to apply to the Title element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.", tab = 2)
    @TextField
    @Inject @Optional
    protected String domId;

    public String getText() {
        if (StringUtils.isNotBlank(text)) {
            return IconUtils.iconify(text);
        }

        return "Heading Text";
    }

    public String getDomId() {
        if (StringUtils.isNotBlank(domId)) {
            return domId;
        }

        if (text != null) {
            return ComponentUtils.sanatizeTextAsDomId(text);
        }

        return "";
    }

    public abstract String getSize();

}
