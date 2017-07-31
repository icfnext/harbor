package com.icfolson.aem.harbor.core.components.content.title.v1;

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
import com.icfolson.aem.library.api.page.enums.TitleType;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

/**
 * Represents the primary title of a page. The text of the title defaults to the
 * authored page title but can be overridden with static text if necessary. The
 * primary title of the page is rendered as an H1 DOM element.
 */
@Component(
    value = "Title (v1)",
    resourceSuperType = AbstractHeading.RESOURCE_TYPE,
    name = "title/v1/title",
    tabs = {
        @Tab(title = "Title"),
        @Tab(title = "Advanced")
    }
)
@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = Title.RESOURCE_TYPE)
public class Title implements Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/title/v1/title";

    private static final String DEFAULT_TEXT = "Title";

    @Inject @Optional
    private String text;

    @Inject @Optional
    private String domId;

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Title", fieldDescription = "The textual content of the rendered title.  Will default to the Page's Page Title if available and will fall back to the Page's Title if available.")
    @TextField
    public String getText() {
        return IconUtils.iconify(getTitleText());
    }

    @DialogField(fieldLabel = "ID",
            fieldDescription = "A unique identifier to apply to the Title element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.",
            tab = 2)
    @TextField
    public String getDomId() {
        return StringUtils.isNotBlank(domId) ? domId : ComponentUtils.sanitizeTextAsDomId(getTitleText());
    }

    /**
     * Always defaults to H1. From an SEO perspective, the primary title of a
     * page should always be H1 and there should only be one H1 element on a
     * page.
     *
     * @return H1
     */
    @Override
    public String getSize() {
        return Headings.H1;
    }

    protected String getTitleText() {
        return StringUtils.isNotBlank(text) ? text :
                currentPage.getTitle(TitleType.PAGE_TITLE)
                        .or(currentPage.getTitle(TitleType.TITLE)
                        .or(DEFAULT_TEXT));
    }

}
