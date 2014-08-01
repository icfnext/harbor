package com.citytechinc.cq.harbor.proper.core.components.content.html;

import org.apache.commons.lang.StringUtils;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.widgets.TextArea;

@Component("HTML")
@AutoInstantiate(instanceName = HTML.INSTANCE_NAME)
public class HTML extends AbstractComponent {

	public static final String INSTANCE_NAME = "htmlComponent";

	@DialogField(fieldLabel = "Source", fieldDescription = "Enter HTML markup directly.  It is recommended that you run your markup through a validator or test it outside the context of the site to ensure its validity before placing it into your content.", additionalProperties = { @FieldProperty(name = "grow", value = "{Boolean}true") })
	@TextArea
	private String HTMLSource;

	@Override
	public void init(ComponentRequest request) {

		HTMLSource = get("HTMLSource", StringUtils.EMPTY);
	}

	public String getHtmlSource() {
		return HTMLSource;
	}

}
