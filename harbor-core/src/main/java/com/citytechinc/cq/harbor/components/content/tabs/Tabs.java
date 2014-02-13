package com.citytechinc.cq.harbor.components.content.tabs;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.library.content.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Tabs",
        actions = {"text: Tabs", "-", "copymove", "delete", "-", "insert"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="[harbor.components.content.tabs,harbor.bootstrap]")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Tab", handler = "function(){Harbor.Components.Tabs.addTab(this)}")
        }
)
public class Tabs extends AbstractComponent {

	private final String name;

    //TODO: remove this once implemented in cqlib
	private final String uniqueId;
	private final List<Tab> tabs;
    public static final String JCR_CONTENT = "jcr:content";

	public Tabs( ComponentRequest request ) {
		super(request);

		this.name = request.getResource().getName();

		this.tabs = new ArrayList<Tab>();
        //TODO: change this to use a unique ID generator
		this.uniqueId = constructUniqueId(request.getResource());

		Iterator<ComponentNode> componentNodeIterator = request.getComponentNode().getComponentNodes().iterator();

		while (componentNodeIterator.hasNext()) {
	        ComponentNode currentComponentNode = componentNodeIterator.next();

			if (currentComponentNode.getResource().getResourceType().equals(Tab.TYPE)) {
				this.tabs.add( new Tab(currentComponentNode) );
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public List<Tab> getTabs() {
		return this.tabs;
	}

	public Boolean getHasTabs() {
		return !this.tabs.isEmpty();
	}

	public String getUniqueId() {
		return this.uniqueId;
	}
    //TODO: remove this once implemented in cqlib
    public static String constructUniqueId( Resource r ) {
		StringBuffer uniqueIdBuffer = new StringBuffer();

		Resource curResource = r;

		while( curResource != null && !curResource.getName().equals(JCR_CONTENT) ) {
			uniqueIdBuffer.append(curResource.getName());
			curResource = curResource.getParent();
		}

		return uniqueIdBuffer.toString();
	}

}
