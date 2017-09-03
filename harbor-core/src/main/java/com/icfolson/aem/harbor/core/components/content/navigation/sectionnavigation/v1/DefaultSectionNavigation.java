package com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePageTree;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(value = "Section Navigation (v1)",
        group = "Harbor Hidden",
        name = "navigation/sectionnavigation/v1/sectionnavigation",
        resourceSuperType = DefaultNavigablePageTree.RESOURCE_TYPE)
@Model(adaptables = Resource.class,
        adapters = TreeComponent.class,
        resourceType = DefaultSectionNavigation.RESOURCE_TYPE)
public class DefaultSectionNavigation extends DefaultNavigablePageTree {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation";

    @Inject
    private PageDecorator currentPage;

    @Override
    public PageDecorator getRootPage() {
        if (currentPage == null) {
            return null;
        }

        return currentPage.adaptTo(HierarchicalPage.class)
                .getSectionLandingPage()
                .transform(PageDecorator::getParent)
                .orNull();
    }

}
