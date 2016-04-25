package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinmainmanualnavigation;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation.BootstrapMainManualNavigation;
import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Bakedin Bootstrap Main Manual Navigation",
    group = ".hidden",
    path = "content/navigation",
    resourceSuperType = BootstrapMainManualNavigation.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Main Manual Navigation", "-", "edit" },
    suppressTouchUIDialog = true)
@AutoInstantiate(instanceName = "bootstrapMainManualNavigation")
@Model(adaptables = Resource.class)
public class BakedinBootstrapMainManualNavigation extends BootstrapMainManualNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapmainmanualnavigation";

}
