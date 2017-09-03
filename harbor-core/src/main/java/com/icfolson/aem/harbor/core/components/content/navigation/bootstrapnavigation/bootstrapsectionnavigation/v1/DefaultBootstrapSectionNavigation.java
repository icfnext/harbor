package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1.DefaultSectionNavigation;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Optional;

@Component(value = "Bootstrap Section Navigation (v1)",
        name = "navigation/bootstrapnavigation/bootstrapsectionnavigation/v1/bootstrapsectionnavigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        resourceSuperType = DefaultSectionNavigation.RESOURCE_TYPE)
@Model(adaptables = Resource.class,
        adapters = {TreeComponent.class},
        resourceType = DefaultBootstrapSectionNavigation.RESOURCE_TYPE)
public class DefaultBootstrapSectionNavigation extends DefaultSectionNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapnavigation/bootstrapsectionnavigation/v1/bootstrapsectionnavigation";

    @Override
    public Optional<Integer> getDepth() {
        return Optional.of(2);
    }

}
