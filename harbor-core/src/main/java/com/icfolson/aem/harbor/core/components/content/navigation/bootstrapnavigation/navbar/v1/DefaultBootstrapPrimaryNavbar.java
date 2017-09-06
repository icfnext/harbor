package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.navbar.v1;

import com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.navbar.BootstrapPrimaryNavbar;
import com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapprimarynavigation.v1.DefaultBootstrapPrimaryNavigation;
import com.icfolson.aem.harbor.core.components.content.navigation.brand.bootstrapbrand.v1.DefaultBootstrapBrand;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = BootstrapPrimaryNavbar.class, resourceType = DefaultBootstrapPrimaryNavbar.RESOURCE_TYPE)
public class DefaultBootstrapPrimaryNavbar implements BootstrapPrimaryNavbar {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavbar/v1/bootstrapprimarynavbar";

    @Self
    private Resource resource;

    @Inject @Optional
    private String navigationToggleScreenReaderLabel;

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

    @Override
    public String getNavigationToggleScreenReaderLabel() {
        return navigationToggleScreenReaderLabel;
    }

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(resource.getPath());
    }

}
