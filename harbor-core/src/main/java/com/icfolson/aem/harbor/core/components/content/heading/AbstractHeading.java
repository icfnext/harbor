package com.icfolson.aem.harbor.core.components.content.heading;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public abstract class AbstractHeading extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/abstractheading";

    private static final String DEFAULT_TEXT = "Heading Text";

    @DialogField(fieldLabel = "Text", fieldDescription = "The textual content of the rendered heading.")
    @TextField
    public String getText() {
        return IconUtils.iconify(getTextValue().or(getDefaultText()));
    }

    @DialogField(fieldLabel = "ID",
        fieldDescription = "A unique identifier to apply to the Title element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.",
        tab = 2)
    @TextField
    public String getDomId() {
        return get("domId", String.class).or(getTextValue()
            .transform(ComponentUtils:: sanitizeTextAsDomId)
            .or(""));
    }

    public abstract String getSize();

    protected Optional<String> getTextValue() {
        return get("text", String.class);
    }

    protected String getDefaultText() {
        return DEFAULT_TEXT;
    }
}
