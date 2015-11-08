package com.citytechinc.aem.harbor.core.components.content.subtitle;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.aem.harbor.api.constants.dom.Headings;
import com.citytechinc.aem.harbor.core.components.content.heading.Heading;
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
        resourceSuperType = Heading.RESOURCE_TYPE,
        tabs = {
                //TODO: Cleanup once touch extension is better handled by the plugin
                @Tab(title = "Heading"),
                @Tab(title = "Advanced")
        }
)
@AutoInstantiate(instanceName = Subtitle.INSTANCE_NAME)
@Model(adaptables = Resource.class)
public class Subtitle extends Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/subtitle";
    public static final String INSTANCE_NAME = "subtitleHeading";

    @Inject
    private PageDecorator currentPage;

    @Override
    @DialogField(fieldLabel = "Subtitle Text", fieldDescription = "The textual content of the rendered subtitle. If left empty, the page subtitle will be rendered.")
    public String getText() {

        return IconUtils.iconify(getRawText());

    }

    /**
     * Always defaults to H2. From an SEO perspective, the secondary title of a
     * page should always be H2.
     *
     * @return H2
     */
    @Override
    @IgnoreDialogField
    public String getSize() {
        return Headings.H2;
    }

    protected String getRawText() {
        String title = get(TEXT_PROPERTY, StringUtils.EMPTY);

        if (StringUtils.isNotBlank(title)) {
            return title;
        }

        return currentPage.get("subtitle", "Subtitle");
    }
}
