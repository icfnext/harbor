package com.icfolson.aem.harbor.core.components.content.text;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.text.Text;
import com.icfolson.aem.harbor.core.components.content.text.v1.DefaultText;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Bakedin Text",
        group = ".hidden",
        actions = {"text: Text", "-", "edit"},
        suppressFieldInheritanceForTouchUI = true,
        suppressTouchUIDialog = true,
        resourceSuperType = DefaultText.RESOURCE_TYPE,
        name = "text/v1/bakedin"
)
@Model(adaptables = Resource.class, adapters = Text.class, resourceType = BakedinText.RESOURCE_TYPE)
public class BakedinText extends DefaultText {

    public final static String RESOURCE_TYPE = "harbor/components/content/text/v1/bakedintext";

}
