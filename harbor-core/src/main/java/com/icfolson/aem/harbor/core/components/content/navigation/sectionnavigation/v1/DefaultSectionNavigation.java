package com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.navigation.page.NavigablePage;
import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.datastructure.tree.TreeNode;
import com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePageTree;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.stream.Collectors;


@Component(value = "Section Navigation (v1)",
        group = "Harbor Hidden",
        name = "navigation/sectionnavigation/v1/sectionnavigation",
        resourceSuperType = DefaultNavigablePageTree.RESOURCE_TYPE)
@Model(adaptables = Resource.class,
        adapters = TreeComponent.class,
        resourceType = DefaultSectionNavigation.RESOURCE_TYPE)
public class DefaultSectionNavigation extends DefaultNavigablePageTree {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation";

    @Override
    public PageDecorator getRootPage() {
        if (getCurrentPage() == null) {
            return null;
        }

        return getCurrentPage().adaptTo(HierarchicalPage.class)
                .getSectionLandingPage()
                .transform(PageDecorator::getParent)
                .orNull();
    }

}