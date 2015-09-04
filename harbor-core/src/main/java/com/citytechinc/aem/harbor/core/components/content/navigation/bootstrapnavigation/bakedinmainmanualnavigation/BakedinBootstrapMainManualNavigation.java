package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinmainmanualnavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation.BootstrapMainManualNavigation;
import com.citytechinc.cq.component.annotations.Component;

@Component(
        value = "Bakedin Bootstrap Main Manual Navigation",
        group = ".hidden",
        path = "content/navigation",
        resourceSuperType = BootstrapMainManualNavigation.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Main Manual Navigation", "-", "edit" })
@AutoInstantiate(instanceName = "bootstrapMainManualNavigation")
public class BakedinBootstrapMainManualNavigation extends BootstrapMainManualNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapmainmanualnavigation";

}
