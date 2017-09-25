package com.icfolson.aem.harbor.core.components.content.navigation.primarynavigation.v1;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePageTree;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,
        adapters = {TreeComponent.class},
        resourceType = DefaultPrimaryNavigation.RESOURCE_TYPE)
public class DefaultPrimaryNavigation extends DefaultNavigablePageTree {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/primarynavigation/v1/primarynavigation";

    @Override
    public PageDecorator getRootPage() {
        if (getCurrentPage() == null) {
            return null;
        }

        Optional<HomePage> homePageOptional = getCurrentPage().getContentResource().adaptTo(HierarchicalPage.class).getHomePage();

        if (homePageOptional.isPresent()) {
            return homePageOptional.get().adaptTo(PageDecorator.class);
        }

        return null;
    }

}
