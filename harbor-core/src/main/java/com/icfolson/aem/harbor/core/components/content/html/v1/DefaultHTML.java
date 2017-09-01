package com.icfolson.aem.harbor.core.components.content.html.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.icfolson.aem.harbor.api.components.content.html.HTML;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(value = "HTML (v1)",
        name = "html/v1/html")
@Model(adaptables = Resource.class, adapters = HTML.class, resourceType = DefaultHTML.RESOURCE_TYPE)
public class DefaultHTML implements HTML {

    public static final String RESOURCE_TYPE = "harbor/components/content/html/v1/html";

    @DialogField(fieldLabel = "Source", fieldDescription = "Enter HTML markup directly.  It is recommended that you run your markup through a validator or test it outside the context of the site to ensure its validity before placing it into your content.", additionalProperties = { @Property(name = "grow", value = "{Boolean}true") })
    @TextArea
    @Inject @Default(values = "HTML Source")
    private String htmlSource;

    public String getHtmlSource() {
        return htmlSource;
    }

}
