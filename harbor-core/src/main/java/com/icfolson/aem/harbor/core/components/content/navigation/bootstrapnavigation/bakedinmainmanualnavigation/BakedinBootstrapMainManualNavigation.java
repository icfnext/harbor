package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinmainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation.BootstrapMainManualNavigation;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Baked-In Bootstrap Main Manual Navigation",
    group = ComponentConstants.GROUP_HIDDEN,
    path = "content/navigation",
    resourceSuperType = BootstrapMainManualNavigation.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Main Manual Navigation", "-", "edit" },
    suppressTouchUIDialog = true)
@Model(adaptables = Resource.class)
public class BakedinBootstrapMainManualNavigation extends BootstrapMainManualNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapmainmanualnavigation";

}
