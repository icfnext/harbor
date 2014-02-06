package com.citytechinc.cq.harbor.components.content.tabs;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;

import com.citytechinc.cqlibrary.web.annotations.Component;
import com.citytechinc.cqlibrary.web.components.AbstractComponent;
import com.citytechinc.cqlibrary.web.content.request.ComponentRequest;
import com.videojet.global.ui.utils.JcrUtils;

@Component
public class Tabs extends AbstractComponent {
	
	private final String name;
	private final String uniqueId;
	private final List<Tab> tabs;
	
	public Tabs( ComponentRequest request ) {
		super(request);
		
		this.name = request.getResource().getName();
		
		this.tabs = new ArrayList<Tab>();
		
		this.uniqueId = JcrUtils.constructUniqueId(request.getResource());
		
		Iterator<Resource> tabResourceIterator = request.getResource().listChildren();
		
		while (tabResourceIterator.hasNext()) {
			Resource curTabResource = tabResourceIterator.next();
			
			if (curTabResource.getResourceType().equals(Tab.TYPE)) {
				this.tabs.add( new Tab(curTabResource) );
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

}
