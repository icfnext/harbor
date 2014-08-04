package com.citytechinc.cq.harbor.proper.core.components.content.tabs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.util.ComponentUtils;
import com.day.cq.wcm.api.Page;

@Component(value = "Tabs", group = ComponentGroups.HARBOR_SCAFFOLDING, actions = { "text: Tabs", "-", "copymove",
	"delete", "-", "insert" }, listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") }, actionConfigs = {
	@ActionConfig(xtype = "tbseparator"),
	@ActionConfig(text = "Add Tab", handler = "function(){Harbor.Components.Tabs.addTab(this)}") }, contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.components.content.tabs]") })
@AutoInstantiate(instanceName = "tabs")
public class Tabs extends AbstractComponent {
	private List<Tab> tabs;

	public static String constructUniqueId(Page page, Resource r) {
		return ComponentUtils.getComponentId(page, r);
	}

	public String getName() {
		return this.getResource().getName();
	}

	public List<Tab> getTabs() {
		this.tabs = new ArrayList<Tab>();
		Iterator<ComponentNode> componentNodeIterator = this.getComponentNodes().iterator();
		while (componentNodeIterator.hasNext()) {
			ComponentNode currentComponentNode = componentNodeIterator.next();

			if (currentComponentNode.getResource().isResourceType(Tab.TYPE)) {
				this.tabs.add(getComponent(currentComponentNode, Tab.class).get());
			}
		}
		return this.tabs;
	}

	public Boolean getHasTabs() {
		return !this.getTabs().isEmpty();
	}

	public String getUniqueId() {
		return constructUniqueId(getCurrentPage(), getResource());
	}

}
