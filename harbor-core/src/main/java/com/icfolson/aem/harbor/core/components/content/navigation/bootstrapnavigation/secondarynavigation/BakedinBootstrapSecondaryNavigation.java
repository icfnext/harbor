package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation;

import com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation.BootstrapSecondaryNavigation;
import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Bakedin Bootstrap Main Manual Navigation",
    group = ".hidden",
    path = "content/navigation",
    resourceSuperType = BootstrapSecondaryNavigation.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Secondary Navigation", "-", "edit" },
    suppressTouchUIDialog = true)
@Model(adaptables = Resource.class)
public class BakedinBootstrapSecondaryNavigation extends BootstrapSecondaryNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bakedinbootstrapsecondarynavigation";

}
