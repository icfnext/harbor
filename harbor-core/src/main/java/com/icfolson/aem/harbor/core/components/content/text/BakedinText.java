package com.icfolson.aem.harbor.core.components.content.text;

import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Bakedin Text",
        group = ".hidden",
        actions = { "text: Text", "-", "edit" },
        suppressFieldInheritanceForTouchUI = true,
        suppressTouchUIDialog = true,
        resourceSuperType = Text.RESOURCE_TYPE,
        name = "text/bakedin"
)
@Model(adaptables = Resource.class)
public class BakedinText extends Text {
}
