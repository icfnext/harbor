package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.v1;

import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1.DefaultSectionNavigation;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Optional;

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
