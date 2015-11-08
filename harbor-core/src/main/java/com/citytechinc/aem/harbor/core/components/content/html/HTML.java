package com.citytechinc.aem.harbor.core.components.content.html;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Component("HTML")
@AutoInstantiate(instanceName = HTML.INSTANCE_NAME)
@Model(adaptables = Resource.class)
public class HTML {

	public static final String INSTANCE_NAME = "htmlComponent";

	@DialogField(fieldLabel = "Source", fieldDescription = "Enter HTML markup directly.  It is recommended that you run your markup through a validator or test it outside the context of the site to ensure its validity before placing it into your content.", additionalProperties = { @Property(name = "grow", value = "{Boolean}true") })
	@TextArea
	@Inject @Named("HTMLSource") @Default(values = "HTML Source")
	private String HTMLSource;

	public String getHtmlSource() {
		return HTMLSource;
	}

}
