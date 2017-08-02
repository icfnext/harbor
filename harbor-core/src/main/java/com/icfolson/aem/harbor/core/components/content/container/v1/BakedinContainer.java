package com.icfolson.aem.harbor.core.components.content.container.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Container (v1)",
    name = "contentcontainer/v1/bakedincontentcontainer",
    group = ComponentConstants.GROUP_HIDDEN,
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    },
    resourceSuperType = DefaultContainer.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Content Container", "-", "edit" })
@Model(adaptables = Resource.class, adapters = Container.class, resourceType = BakedinContainer.RESOURCE_TYPE)
public class BakedinContainer extends DefaultContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/contentcontainer/v1/bakedincontentcontainer";

    @Override
    public boolean isInherits() {
        return true;
    }
}
