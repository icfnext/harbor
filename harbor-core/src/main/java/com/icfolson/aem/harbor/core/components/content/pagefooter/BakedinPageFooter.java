package com.icfolson.aem.harbor.core.components.content.pagefooter;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Bakedin Page Footer",
    group = ".hidden",
    tabs = {
            @Tab(title = "Container"),
            @Tab(title = "Advanced")
    },
    resourceSuperType = PageFooter.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Page Footer", "-", "edit" } )
@Model(adaptables = Resource.class)
public class BakedinPageFooter extends PageFooter {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinpagefooter";

}
