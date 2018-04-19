package com.icfolson.aem.harbor.core.components.content.title.v1;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.core.components.content.heading.v1.AbstractHeading;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.enums.TitleType;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Represents the primary title of a page. The text of the title defaults to the
 * authored page title but can be overridden with static text if necessary. The
 * primary title of the page is rendered as an H1 DOM element.
 */
@Model(adaptables = Resource.class, adapters = {Heading.class, ComponentExporter.class}, resourceType = Title.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class Title extends AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/title/v1/title";

    private static final String DEFAULT_TEXT = "Title";

    @Inject @Optional
    private String text;

    @Inject
    private PageDecorator currentPage;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Title", fieldDescription = "The textual content of the rendered title.  Will default to the Page's Page Title if available and will fall back to the Page's Title if available.")
    @TextField
    public String getText() {
        return getTitleText();
    }

    public String getDomId() {
        return ComponentUtils.sanitizeTextAsDomId(getTitleText());
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

    @Nonnull
    @Override
    public String getExportedType() {
        return resource.getResourceType();
    }

    protected String getTitleText() {
        return StringUtils.isNotBlank(getRawText()) ? getRawText() :
                currentPage.getTitle(TitleType.PAGE_TITLE)
                        .or(currentPage.getTitle(TitleType.TITLE)
                        .or(DEFAULT_TEXT));
    }

    protected String getRawText() {
        return text;
    }
}
