package com.icfolson.aem.harbor.core.components.content.subtitle.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.core.components.content.heading.v1.AbstractHeading;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

/**
 * Represents the secondary title of a page. The text of the title defaults to the
 * authored page subtitle but can be overridden with static text if necessary. The
 * secondary title of the page is rendered as an H2 DOM element.
 */
@Component(
    value = "Subtitle (v1)",
    resourceSuperType = AbstractHeading.RESOURCE_TYPE,
    name = "subtitle/v1/subtitle",
    tabs = {
            @Tab(title = "Subtitle"),
            @Tab(title = "Advanced")
    }
)
@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = Subtitle.RESOURCE_TYPE)
public class Subtitle implements Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/subtitle/v1/subtitle";

    private static final String DEFAULT_TEXT = "Subtitle";

    @Inject @Optional
    private String text;

    @Inject @Optional
    private String domId;

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Subtitle", fieldDescription = "The textual content of the rendered subtitle.  Will default to the Page's configured subtitle if available.")
    @TextField
    public String getText() {
        return IconUtils.iconify(getSubtitleText());
    }

    @DialogField(fieldLabel = "ID",
            fieldDescription = "A unique identifier to apply to the Subtitle element rendered in the page DOM.  This will default to a sanitized version of the text content of the subtitle if not overridden.",
            tab = 2)
    @TextField
    public String getDomId() {
        return !StringUtils.isBlank(domId) ? domId : ComponentUtils.sanitizeTextAsDomId(getSubtitleText());
    }

    /**
     * Always defaults to H2. From an SEO perspective, the secondary title of a
     * page should always be H2.
     *
     * @return H2
     */
    @Override
    public String getSize() {
        return Headings.H2;
    }

    protected String getSubtitleText() {
        return StringUtils.isNotBlank(text) ? text : currentPage.get("subtitle", Subtitle.DEFAULT_TEXT);
    }

}
