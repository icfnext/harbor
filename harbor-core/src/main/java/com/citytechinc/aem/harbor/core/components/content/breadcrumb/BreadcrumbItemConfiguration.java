package com.citytechinc.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;

public class BreadcrumbItemConfiguration {

	private final ComponentNode componentNode;
	private final String propertyNamePrefix;

	private Boolean hideTitle;
	private Boolean hideIcon;

	public final String HIDE_ICON_PROPERTY_NAME = "hideIcon";
	public final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";

	public BreadcrumbItemConfiguration(ComponentNode componentNode, String propertyNamePrefix) {
		this.componentNode = componentNode;
		this.propertyNamePrefix = propertyNamePrefix;
	}

	@DialogField(fieldLabel = "Hide Icon")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	public Boolean getHideIcon() {
		if (hideIcon == null) {
			hideIcon = componentNode.get(propertyNamePrefix + HIDE_ICON_PROPERTY_NAME, false);
		}

		return hideIcon;
	}

	@DialogField(fieldLabel = "Hide Title")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	public Boolean getHideTitle() {
		if (hideTitle == null) {
			hideTitle = componentNode.get(propertyNamePrefix + HIDE_TITLE_PROPERTY_NAME, false);
		}

		return hideTitle;
	}

}
