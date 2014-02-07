package com.citytechinc.cq.harbor.components.content.tabs;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Tabs",
        actions = {"text: Tabs", "-", "edit", "-", "copymove", "delete", "-", "insert"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.tabs")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        }
)
public class Tabs extends AbstractComponent {
	
	private final String name;
	private final String uniqueId;
	private final List<Tab> tabs;
	
	public Tabs( ComponentRequest request ) {
		super(request);
		
		this.name = request.getResource().getName();
		
		this.tabs = new ArrayList<Tab>();
        //TODO: change this to use a unique ID generator
		this.uniqueId = request.getComponentNode().getPath();
		
		Iterator<PageDecorator> pageDecoratorIterator = request.getCurrentPage().getChildren().iterator();
		
		while (pageDecoratorIterator.hasNext()) {
			PageDecorator currentPageDecorator = pageDecoratorIterator.next();
			
			if (currentPageDecorator.getContentResource().getResourceType().equals(Tab.TYPE)) {
				this.tabs.add( new Tab(currentPageDecorator.getComponentNode().get()) );
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
