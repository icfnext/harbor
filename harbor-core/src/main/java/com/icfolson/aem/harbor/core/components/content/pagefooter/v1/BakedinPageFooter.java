package com.icfolson.aem.harbor.core.components.content.pagefooter.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Page Footer",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "pagefooter/v1/bakedinpagefooter",
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    },
    resourceSuperType = PageFooter.RESOURCE_TYPE,
    disableTargeting = true,
    isContainer = true,
    actions = { "text: Page Footer", "-", "edit" })
@Model(adaptables = Resource.class, adapters = Container.class, resourceType = BakedinPageFooter.RESOURCE_TYPE)
public class BakedinPageFooter extends PageFooter {

    public static final String RESOURCE_TYPE = "harbor/components/content/pagefooter/v1/bakedinpagefooter";

}
