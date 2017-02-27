package com.icfolson.aem.harbor.core.components.content.title;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import com.icfolson.aem.harbor.core.components.content.heading.AbstractHeading;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * Represents the primary title of a page. The text of the title defaults to the
 * authored page title but can be overridden with static text if necessary. The
 * primary title of the page is rendered as an H1 DOM element.
 */
@Component(
    value = "Title",
    resourceSuperType = AbstractHeading.RESOURCE_TYPE,
    tabs = {
        @Tab(title = "Title"),
        @Tab(title = "Advanced")
    }
)
@Model(adaptables = Resource.class)
public class Title extends AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/title";

    private static final String DEFAULT_TEXT = "Title";

    @Inject
    private PageDecorator currentPage;

    @Override
    protected String getTextValue() {
        if (StringUtils.isNotBlank(super.getTextValue())) {
            return super.getTextValue();
        } else if (StringUtils.isNotBlank(currentPage.getPageTitle())) {
            return currentPage.getPageTitle();
        } else if (StringUtils.isNoneBlank(currentPage.getTitle())) {
            return currentPage.getTitle();
        }

        return getDefaultText();
    }

    /**
     * Always defaults to H1. From an SEO perspective, the primary title of a
     * page should always be H1 and there should only be one H1 element on a
     * page.
     *
     * @return H1
     */
    public String getSize() {
        return Headings.H1;
    }

    @Override
    protected String getDefaultText() {
        return DEFAULT_TEXT;
    }
}
