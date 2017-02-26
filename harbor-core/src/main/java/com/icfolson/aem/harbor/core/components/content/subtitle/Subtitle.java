package com.icfolson.aem.harbor.core.components.content.subtitle;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.core.components.content.heading.AbstractHeading;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.commons.lang3.StringUtils;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * Represents the secondary title of a page. The text of the title defaults to the
 * authored page subtitle but can be overridden with static text if necessary. The
 * secondary title of the page is rendered as an H2 DOM element.
 */
@Component(
        value = "Subtitle",
        resourceSuperType = AbstractHeading.RESOURCE_TYPE,
        tabs = {
                @Tab(title = "Subtitle"),
                @Tab(title = "Advanced")
        }
)
@Model(adaptables = Resource.class)
public class Subtitle extends AbstractHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/subtitle";

    @Inject
    private PageDecorator currentPage;

    public String getText() {
        if (StringUtils.isNotBlank(super.getTextValue())) {
            return super.getText();
        }

        return IconUtils.iconify(currentPage.get("subtitle", "Subtitle"));
    }

    /**
     * Always defaults to H2. From an SEO perspective, the secondary title of a
     * page should always be H2.
     *
     * @return H2
     */
    public String getSize() {
        return Headings.H2;
    }

}
