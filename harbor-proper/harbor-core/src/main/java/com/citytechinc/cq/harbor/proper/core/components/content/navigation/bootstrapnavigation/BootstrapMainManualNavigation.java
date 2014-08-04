package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;

@Component(value = "Bootstrap Main Manual Navigation", group = ComponentGroups.HARBOR_NAVIGATION, actions = {
	"text:Bootstrap Main Manual Navigation", "-", "edit", "-", "delete" }, contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.components.content.bootstrapmainmanualnavigation,harbor.bootstrap.navbar]") }, actionConfigs = {
	@ActionConfig(xtype = "tbseparator"),
	@ActionConfig(text = "Add Navigation Column", handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"), }, listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") }, allowedParents = "*/parsys")
@AutoInstantiate(instanceName = "bootstrapMainManualNavigation")
public class BootstrapMainManualNavigation extends AbstractComponent {

	private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

	@Override
	public void init(ComponentRequest request) {

		bootstrapMainNavigationElementList = new ArrayList<BootstrapMainNavigationElement>();
		// Add The child elements of our GlobalNav to the Nav Element list
		Iterator<Resource> navigationResourceIterator = request.getResource().listChildren();

		while (navigationResourceIterator.hasNext()) {
			this.bootstrapMainNavigationElementList.add(getComponent(navigationResourceIterator.next().getPath(),
				BootstrapMainNavigationElement.class).get());
		}
	}

	@DialogField(fieldLabel = "Enable Sticky Navigation?", fieldDescription = "")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	public Boolean getStickyNavigationEnabled() {
		return getInherited("stickyNavigationEnabled", "").equals("true");
	}

	public List<BootstrapMainNavigationElement> getBootstrapMainNavigationElementList() {
		return bootstrapMainNavigationElementList;
	}

}
