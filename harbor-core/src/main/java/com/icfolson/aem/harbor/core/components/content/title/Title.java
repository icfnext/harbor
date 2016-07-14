package com.icfolson.aem.harbor.core.components.content.title;

import com.icfolson.aem.harbor.core.components.content.heading.AbstractHeading;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.citytechinc.cq.component.annotations.*;
import org.apache.commons.lang3.StringUtils;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.harbor.api.constants.dom.Headings;
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
@AutoInstantiate(instanceName = Title.INSTANCE_NAME)
@Model(adaptables = Resource.class)
public class Title extends AbstractHeading {

	public static final String RESOURCE_TYPE = "harbor/components/content/title";

	@Inject
	private PageDecorator currentPage;

	public String getText() {
		if (StringUtils.isNotBlank(text)) {
			return super.getText();
		}

		if (StringUtils.isNotBlank(currentPage.getPageTitle())) {
			return IconUtils.iconify(currentPage.getPageTitle());
		}

		return IconUtils.iconify(currentPage.getTitle());
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

}
