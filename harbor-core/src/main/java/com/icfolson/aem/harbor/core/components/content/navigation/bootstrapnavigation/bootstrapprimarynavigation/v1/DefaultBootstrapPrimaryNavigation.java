package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapprimarynavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.bootstrapprimarynavigation.BootstrapPrimaryNavigation;
import com.icfolson.aem.harbor.api.components.content.navigation.primarynavigation.PrimaryNavigation;
import com.icfolson.aem.harbor.api.components.content.navigation.sitenavigation.SiteNavigation;
import com.icfolson.aem.harbor.core.components.content.navigation.primarynavigation.v1.DefaultPrimaryNavigation;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Optional;

@Component(value = "Bootstrap Primary Navigation (v1)",
        group = ".hidden",
        actions = { "text: Bootstrap Primary Navigation (v1)", "-", "edit" },
        name = "navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation",
        resourceSuperType = DefaultPrimaryNavigation.RESOURCE_TYPE)
@Model(adaptables = Resource.class, adapters = { BootstrapPrimaryNavigation.class, PrimaryNavigation.class, SiteNavigation.class }, resourceType = DefaultBootstrapPrimaryNavigation.RESOURCE_TYPE)
public class DefaultBootstrapPrimaryNavigation extends DefaultPrimaryNavigation implements BootstrapPrimaryNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation";

    @Override
    public Optional<Integer> getDepth() {
        return Optional.of(2);
    }

}
