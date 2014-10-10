package com.citytechinc.aem.harbor.core.components.content.title;

import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.ContentProperty;
import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.aem.harbor.api.constants.dom.Headings;
import com.citytechinc.aem.harbor.core.components.content.heading.Heading;

/**
 * Represents the primary title of a page. The text of the title defaults to the
 * authored page title but can be overridden with static text if necessary. The
 * primary title of the page is rendered as an H1 DOM element.
 */
@Component(
        value = "Title",
        resourceSuperType = Heading.RESOURCE_TYPE,
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.fontawesome]") }
)
@AutoInstantiate(instanceName = Title.INSTANCE_NAME)
public class Title extends Heading {

	public static final String RESOURCE_TYPE = "harbor/components/content/title";
	public static final String INSTANCE_NAME = "titleHeading";

	@Override
	@DialogField(fieldLabel = "Title Text", fieldDescription = "The textual content of the rendered title. If left empty, the page title will be rendered.")
	public String getText() {

		String title = get(TEXT_PROPERTY, StringUtils.EMPTY);

		if (StringUtils.isNotBlank(title)) {
			return IconUtils.iconify(title);
		}

		if (StringUtils.isNotEmpty(getCurrentPage().getPageTitle())) {
			return IconUtils.iconify(getCurrentPage().getPageTitle());
		}

		return IconUtils.iconify(getCurrentPage().getTitle());

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

}
