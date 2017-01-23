package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.list.AbstractTreeComponent;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.content.page.navigation.construction.SiteNavigationListConstructionStrategy;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(
    value = "Main Auto Navigation",
    group = ComponentGroups.HARBOR_NAVIGATION,
    actions = {
        "text:Main Auto Navigation", "-", "edit", "-", "copymove", "delete" },
    contentAdditionalProperties = {
        @ContentProperty(name = "dependencies", value = "[harbor.bootstrap.navbar,harbor.navigation.mainnavigation]")
    },
    name = "navigation/bootstrapmainautonavigation",
    allowedParents = "*/parsys",
    tabs = {
        @Tab(title = "Main Auto Navigation", touchUINodeName = BootstrapMainAutoNavigation.TAB_1_NODE_NAME)
    })
@Model(adaptables = Resource.class)
public class BootstrapMainAutoNavigation extends
    AbstractTreeComponent<NavigablePage, BootstrapPageNavigableRenderableTree> {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainautonavigation";

    public static final String TAB_1_NODE_NAME = "mainnavigation";

    @Inject
    private PageDecorator currentPage;

    @DialogField(ranking = 1)
    @DialogFieldSet(title = "Page Navigation Construction")
    @Inject
    @Self
    private SiteNavigationListConstructionStrategy constructionStrategy;

    @DialogField(ranking = 10)
    @DialogFieldSet(title = "Page Navigation Rendering")
    @Inject
    @Self
    private BootstrapMainNavigationRenderingStrategy renderingStrategy;

    @Override
    protected TreeConstructionStrategy<NavigablePage> getTreeConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> getTreeRenderingStrategy() {
        return renderingStrategy;
    }

    public boolean isOnStructuralPage() {
        HierarchicalPage hierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

        return hierarchicalPage != null && hierarchicalPage.isStructuralPage();
    }
}
