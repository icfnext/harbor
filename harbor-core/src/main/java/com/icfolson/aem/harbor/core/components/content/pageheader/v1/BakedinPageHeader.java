package com.icfolson.aem.harbor.core.components.content.pageheader.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Page Header (v1)",
    group = ComponentConstants.GROUP_HIDDEN,
    name = "pageheader/v1/bakedinpageheader",
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    },
    resourceSuperType = PageHeader.RESOURCE_TYPE,
    disableTargeting = true,
    isContainer = true,
    actions = { "text: Page Header", "-", "edit" })
@Model(adaptables = Resource.class, adapters = Container.class, resourceType = BakedinPageHeader.RESOURCE_TYPE)
public class BakedinPageHeader extends PageHeader {

    public static final String RESOURCE_TYPE = "harbor/components/content/pageheader/v1/bakedinpageheader";

}
