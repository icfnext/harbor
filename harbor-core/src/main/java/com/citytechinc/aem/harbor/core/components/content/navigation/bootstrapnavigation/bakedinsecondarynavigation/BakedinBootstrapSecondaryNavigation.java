package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinsecondarynavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation.BootstrapSecondaryNavigation;
import com.citytechinc.cq.component.annotations.Component;

@Component(
        value = "Bakedin Bootstrap Main Manual Navigation",
        group = ".hidden",
        path = "content/navigation",
        resourceSuperType = BootstrapSecondaryNavigation.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Secondary Navigation", "-", "edit" })
@AutoInstantiate(instanceName = "secondaryNavigation")
public class BakedinBootstrapSecondaryNavigation extends BootstrapSecondaryNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapsecondarynavigation";

}
