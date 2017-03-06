package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Breadcrumb",
    group = ComponentConstants.GROUP_HIDDEN,
    resourceSuperType = Breadcrumb.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Breadcrumb", "-", "edit" },
    suppressFieldInheritanceForTouchUI = true,
    tabs = {
        @Tab(title = "Breadcrumb", touchUINodeName = "breadcrumb")
    }
)
@Model(adaptables = Resource.class)
public class BakedinBreadcrumb extends InheritingBreadcrumb {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinbreadcrumb";

}
