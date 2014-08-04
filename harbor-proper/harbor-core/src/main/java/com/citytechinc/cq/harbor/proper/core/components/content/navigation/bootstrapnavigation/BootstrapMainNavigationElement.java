package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;

@Component(value = "Navigation Element", actions = { "text: Navigation Element", "-", "edit", "-", "delete" }, listeners = {
	@Listener(name = "afterdelete", value = "REFRESH_PARENT"), @Listener(name = "afteredit", value = "REFRESH_PARENT"), }, group = ".hidden")
@AutoInstantiate(instanceName = BootstrapMainNavigationElement.INSTANCE_NAME)
public class BootstrapMainNavigationElement extends AbstractComponent {

	public static final String INSTANCE_NAME = "navElement";

	@DialogField(fieldLabel = "Element Title")
	public String getElementTitle() {
		return get("elementTitle", this.getResource().getName());
	}

	@DialogField(fieldLabel = "Element Link Target")
	@PathField
	public String getElementLinkTarget() {
		return get("elementLinkTarget", "#");
	}

	@DialogField(fieldLabel = "Has Dropdown?", fieldDescription = "This navigation element will be a dropdown/flyout element")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	public Boolean getHasDropdown() {
		return get("hasDropdown", "").equals("true");
	}

	public String getName() {
		return this.getResource().getName();
	}
}
