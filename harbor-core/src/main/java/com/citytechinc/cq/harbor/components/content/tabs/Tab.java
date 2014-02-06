package com.citytechinc.cq.harbor.components.content.tabs;

import org.apache.sling.api.resource.Resource;

import com.citytechinc.cqlibrary.web.annotations.Component;
import com.citytechinc.cqlibrary.web.components.AbstractComponent;
import com.citytechinc.cqlibrary.web.content.component.impl.ComponentPropertiesImpl;
import com.citytechinc.cqlibrary.web.content.request.ComponentRequest;
import com.videojet.global.ui.utils.JcrUtils;

@Component
public class Tab extends AbstractComponent {

	public static final String TYPE = "videojet-global/components/content/tabs/tab";
	
	private final String title;
	private final String name;
	private final String uniqueId;
	
	public Tab (ComponentRequest request) {
		this(request.getResource());
	}
	
	public Tab (Resource resource) {
		super(new ComponentPropertiesImpl(resource));
		
		this.name = resource.getName();
		this.title = this.get("title", this.name);
		this.uniqueId = JcrUtils.constructUniqueId(resource);
		
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUniqueId() {
		return this.uniqueId;
	}
}
