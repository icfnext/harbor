package com.icfolson.aem.harbor.core.components.content.heading;

import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Bakedin Heading",
        group = ".hidden",
        actions = { "text: Heading", "-", "edit" },
        suppressFieldInheritanceForTouchUI = true,
        suppressTouchUIDialog = true,
        resourceSuperType = Heading.RESOURCE_TYPE,
        name = "heading/bakedin"
)
@Model(adaptables = Resource.class)
public class BakedinHeading extends Heading {
}
