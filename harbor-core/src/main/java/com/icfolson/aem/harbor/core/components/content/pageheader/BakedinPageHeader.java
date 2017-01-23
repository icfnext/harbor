package com.icfolson.aem.harbor.core.components.content.pageheader;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Page Header",
    group = ComponentConstants.GROUP_HIDDEN,
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    },
    resourceSuperType = PageHeader.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Page Header", "-", "edit" })
@Model(adaptables = Resource.class)
public class BakedinPageHeader extends PageHeader {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinpageheader";

}
