package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.list.AbstractTreeComponent;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.content.page.navigation.construction.HierarchicalPageNavigationListConstructionStrategy;
import com.icfolson.aem.harbor.core.trees.rendering.PassthroughTree;
import com.icfolson.aem.harbor.core.trees.rendering.PassthroughTreeRenderingStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(
    value = "Secondary Navigation",
    group = ComponentGroups.HARBOR_NAVIGATION,
    path = "content/navigation",
    allowedParents = "*/parsys")
@Model(adaptables = Resource.class)
public class BootstrapSecondaryNavigation extends AbstractTreeComponent<NavigablePage, PassthroughTree<NavigablePage>> {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapsecondarynavigation";

    @DialogField
    @DialogFieldSet
    @Inject
    @Self
    private HierarchicalPageNavigationListConstructionStrategy constructionStrategy;

    private final TreeRenderingStrategy<NavigablePage, PassthroughTree<NavigablePage>> renderingStrategy = new PassthroughTreeRenderingStrategy<>();

    @Override
    public HierarchicalPageNavigationListConstructionStrategy getTreeConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected TreeRenderingStrategy<NavigablePage, PassthroughTree<NavigablePage>> getTreeRenderingStrategy() {
        return renderingStrategy;
    }
}
