package com.citytechinc.cq.harbor.proper.core.components.content.tabs;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;

@Component(value = "Tab", name = "tabs/tab", actions = { "text: Tab", "edit", "delete" }, listeners = {
	@Listener(name = "afterinsert", value = "REFRESH_PAGE"), @Listener(name = "afteredit", value = "REFRESH_PARENT"),
	@Listener(name = "afterdelete", value = "REFRESH_PARENT") }, group = ".hidden")
@AutoInstantiate(instanceName = "tab")
public class Tab extends AbstractComponent {

	public static final String TYPE = "harbor/components/content/tabs/tab";

	@DialogField(fieldLabel = "Title", fieldDescription = "The title to be presented within the Tab")
	public String getTitle() {
		return this.get("title", this.getName());
	}

	public String getName() {
		return getResource().getName();
	}

	public String getUniqueId() {
		return Tabs.constructUniqueId(getCurrentPage(), getResource());
	}
}