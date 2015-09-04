package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.bakedinmainautonavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation.BootstrapMainAutoNavigation;
import com.citytechinc.cq.component.annotations.Component;

@Component(
        value = "Bakedin Bootstrap Main Auto Navigation",
        group = ".hidden",
        path = "content/navigation",
        resourceSuperType = BootstrapMainAutoNavigation.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Main Auto Navigation", "-", "edit" })
@AutoInstantiate(instanceName = "bootstrapMainAutoNavigation")
public class BakedinBootstrapMainAutoNavigation extends BootstrapMainAutoNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapmainautonavigation";

}
