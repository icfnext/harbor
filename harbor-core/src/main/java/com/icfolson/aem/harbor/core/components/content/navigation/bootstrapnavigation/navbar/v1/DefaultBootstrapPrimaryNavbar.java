package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.navbar.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.navbar.BootstrapPrimaryNavbar;
import com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapprimarynavigation.v1.DefaultBootstrapPrimaryNavigation;
import com.icfolson.aem.harbor.core.components.content.navigation.brand.bootstrapbrand.v1.DefaultBootstrapBrand;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Bootstrap Primary Navbar (v1)",
    group = ComponentGroups.HARBOR_NAVIGATION,
    name = "navigation/bootstrapnavigation/bootstrapprimarynavbar/v1/bootstrapprimarynavbar")
@Model(adaptables = Resource.class, adapters = BootstrapPrimaryNavbar.class, resourceType = DefaultBootstrapPrimaryNavbar.RESOURCE_TYPE)
public class DefaultBootstrapPrimaryNavbar implements BootstrapPrimaryNavbar {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavbar/v1/bootstrapprimarynavbar";

    @Override
    public boolean isSticky() {
        return false;
    }

    @Override
    public boolean isFullWidth() {
        return true;
    }

    @Override
    public String getBrandResourceType() {
        return DefaultBootstrapBrand.RESOURCE_TYPE;
    }

    @Override
    public String getPrimaryNavigationResourceType() {
        return DefaultBootstrapPrimaryNavigation.RESOURCE_TYPE;
    }

}
