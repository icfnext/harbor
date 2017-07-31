package com.icfolson.aem.harbor.core.components.content.heading.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.heading.Heading;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Bakedin Heading (v1)",
        group = ".hidden",
        actions = { "text: Heading", "-", "edit" },
        suppressFieldInheritanceForTouchUI = true,
        suppressTouchUIDialog = true,
        resourceSuperType = DefaultHeading.RESOURCE_TYPE,
        name = "heading/v1/bakedinheading"
)
@Model(adaptables = Resource.class, adapters = Heading.class, resourceType = BakedinHeading.RESOURCE_TYPE)
public class BakedinHeading extends DefaultHeading {

    public static final String RESOURCE_TYPE = "harbor/components/content/heading/v1/bakedinheading";

}
