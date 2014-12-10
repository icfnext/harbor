package com.citytechinc.aem.harbor.core.components.content.subtitle;

import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.aem.harbor.api.constants.dom.Headings;
import com.citytechinc.aem.harbor.core.components.content.heading.Heading;

/**
 * Represents the secondary title of a page. The text of the title defaults to the
 * authored page subtitle but can be overridden with static text if necessary. The
 * secondary title of the page is rendered as an H2 DOM element.
 */
@Component(
        value = "Subtitle",
        resourceSuperType = Heading.RESOURCE_TYPE,
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.fontawesome]") },
        tabs = {
                @Tab(title = "Subtitle"),
                @Tab(title = "Advanced")
        }
)
@AutoInstantiate(instanceName = Subtitle.INSTANCE_NAME)
public class Subtitle extends Heading {

    public static final String RESOURCE_TYPE = "harbor/components/content/subtitle";
    public static final String INSTANCE_NAME = "subtitleHeading";

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
    public String getSize() {
        return Headings.H2;
    }

    protected String getRawText() {
        String title = get(TEXT_PROPERTY, StringUtils.EMPTY);

        if (StringUtils.isNotBlank(title)) {
            return title;
        }

        return getCurrentPage().get("subtitle", StringUtils.EMPTY);
    }
}
