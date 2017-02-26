package com.icfolson.aem.harbor.core.components.content.heading;

import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public abstract class AbstractHeading extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/abstractheading";

    private String text;
    private String domId;

    @DialogField(fieldLabel = "Text", fieldDescription = "The textual content of the rendered heading.")
    @TextField
    public String getText() {
        if (StringUtils.isBlank(text)) {
            text = getTextValue();
            if (StringUtils.isNotBlank(text)) {
                text = IconUtils.iconify(text);
            }
        }

        return StringUtils.defaultIfBlank(text, "Heading Text");
    }

    @DialogField(fieldLabel = "ID", fieldDescription = "A unique identifier to apply to the Title element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.", tab = 2)
    @TextField
    public String getDomId() {
        domId = StringUtils.defaultString(domId, get("domId", String.class).or(StringUtils.EMPTY));
        if (StringUtils.isNotBlank(domId)) {
            return domId;
        }

        if (text != null) {
            return ComponentUtils.sanitizeTextAsDomId(text);
        }

        return StringUtils.EMPTY;
    }

    protected String getTextValue() {
        return get("text", String.class).or(StringUtils.EMPTY);
    }

    public abstract String getSize();
}
