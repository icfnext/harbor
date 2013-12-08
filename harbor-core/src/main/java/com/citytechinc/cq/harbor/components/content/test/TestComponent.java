package com.citytechinc.cq.harbor.components.content.test;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component("Test Component")
public class TestComponent extends AbstractComponent {
	
	public TestComponent(ComponentRequest request) {
		super(request);
	}

	@DialogField(fieldLabel = "Test Title", fieldDescription = "Our title")
	public String getTitle() {
		return get("title","");
	}

}
