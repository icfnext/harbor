package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.icfolson.aem.library.api.node.ComponentNode;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;

public class BreadcrumbItemConfiguration {

	private final ComponentNode componentNode;
	private final String propertyNamePrefix;
	private final boolean inherits;

	private Boolean hideTitle;
	private Boolean hideIcon;

	public final String HIDE_ICON_PROPERTY_NAME = "hideIcon";
	public final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";

	public BreadcrumbItemConfiguration(ComponentNode componentNode, String propertyNamePrefix, boolean inherits) {
		this.componentNode = componentNode;
		this.propertyNamePrefix = propertyNamePrefix;
		this.inherits = inherits;
	}

	@DialogField(fieldLabel = "Hide Icon")
	@Switch(offText = "No", onText = "Yes")
	public Boolean getHideIcon() {
		if (hideIcon == null) {
			hideIcon = inherits ? componentNode.getInherited(propertyNamePrefix + HIDE_ICON_PROPERTY_NAME, false) : componentNode.get(propertyNamePrefix + HIDE_ICON_PROPERTY_NAME, false);
		}

		return hideIcon;
	}

	@DialogField(fieldLabel = "Hide Title")
	@Switch(offText = "No", onText = "Yes")
	public Boolean getHideTitle() {
		if (hideTitle == null) {
			hideTitle = inherits ? componentNode.getInherited(propertyNamePrefix + HIDE_TITLE_PROPERTY_NAME, false) : componentNode.get(propertyNamePrefix + HIDE_TITLE_PROPERTY_NAME, false);
		}

		return hideTitle;
	}

}
