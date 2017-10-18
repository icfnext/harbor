package com.icfolson.aem.harbor.core.components.content.html.v1;

import com.icfolson.aem.harbor.api.components.content.html.HTML;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = HTML.class, resourceType = InheritingHTML.RESOURCE_TYPE)
public class InheritingHTML extends DefaultHTML {

    public static final String RESOURCE_TYPE = DefaultHTML.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Default(values = "HTML Source")
    private String htmlSource;

    public String getHtmlSource() {
        return htmlSource;
    }

}
