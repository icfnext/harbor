package com.citytechinc.aem.harbor.core.components.content.heading;

import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.*;
import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.constants.dom.Headings;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

/**
 * The Heading component is intended to be used to title content sections. The
 * only options available to the Heading from a size perspective are H2-H6 as it
 * is <em>not</em> intended to represent a page title. The Title component
 * should be used if a Page Title is intended.
 */
@Component(
        value = "Heading",
		tabs = {
				@Tab(title = "Heading"),
				@Tab(title = "Advanced")
		}
)
@AutoInstantiate(instanceName = Heading.INSTANCE_NAME)
@Model(adaptables = Resource.class)
public class Heading extends AbstractComponent {

	public static final String RESOURCE_TYPE = "harbor/components/content/heading";
	public static final String INSTANCE_NAME = "heading";

	public static final String SIZE_PROPERTY = "size";
	public static final String TEXT_PROPERTY = "text";

	@DialogField(fieldLabel = "Heading Type", fieldDescription = "The type or size of heading to render.")
	@Selection(type = "select", options = { @Option(text = Headings.H2_LABEL, value = Headings.H2),
		@Option(text = Headings.H3_LABEL, value = Headings.H3), @Option(text = Headings.H4_LABEL, value = Headings.H4),
		@Option(text = Headings.H5_LABEL, value = Headings.H5), @Option(text = Headings.H6_LABEL, value = Headings.H6) })
	public String getSize() {
		return get(SIZE_PROPERTY, Headings.H2);
	}

	@DialogField(fieldLabel = "Heading Text", fieldDescription = "The textual content of the rendered heading.")
	public String getText() {
		return IconUtils.iconify(getRawText());
	}

	protected String getRawText() {
		return get(TEXT_PROPERTY, StringUtils.EMPTY);
	}

	@DialogField(
			fieldLabel = "ID",
			fieldDescription = "A unique identifier to apply to the Title element rendered in the page DOM.  This will default to a sanitized version of the text content of the heading if not overridden.",
			tab = 2)
	public String getDomId() {
		String domId = get("domId", StringUtils.EMPTY);

		if (StringUtils.isNotBlank(domId)) {
			return domId;
		}

		return ComponentUtils.sanatizeTextAsDomId(getRawText());
	}
}
