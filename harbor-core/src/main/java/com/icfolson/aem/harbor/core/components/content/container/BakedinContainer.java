package com.icfolson.aem.harbor.core.components.content.container;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Container",
    name = "bakedincontentcontainer",
    group = ComponentConstants.GROUP_HIDDEN,
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    },
    resourceSuperType = Container.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Content Container", "-", "edit" })
@Model(adaptables = Resource.class)
public class BakedinContainer extends Container {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedincontentcontainer";

    @Override
    protected boolean isInherits() {
        return true;
    }
}
