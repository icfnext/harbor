package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.secondarynavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.trees.rendering.TreeRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractRootedListComponent;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction.HierarchicalPageNavigationListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.trees.rendering.PassthroughTree;
import com.citytechinc.cq.harbor.proper.core.trees.rendering.PassthroughTreeRenderingStrategy;

@Component(
        value = "Secondary Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        path = "content/navigation",
        listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "harbor.bootstrap.navs") },
        allowedParents = "*/parsys" )
@AutoInstantiate(instanceName = "secondaryNavigation")
public class BootstrapSecondaryNavigation extends AbstractRootedListComponent<NavigablePage, PassthroughTree<NavigablePage>> {

	@DialogField
	@DialogFieldSet(title = "Secondary Navigation Construction")
	private HierarchicalPageNavigationListConstructionStrategy constructionStrategy;
	private TreeRenderingStrategy<NavigablePage, PassthroughTree<NavigablePage>> renderingStrategy;

	@Override
	public HierarchicalPageNavigationListConstructionStrategy getTreeConstructionStrategy() {
		if (constructionStrategy == null) {
			constructionStrategy = getComponent(this, HierarchicalPageNavigationListConstructionStrategy.class).get();
		}

		return constructionStrategy;
	}

    @Override
    protected TreeRenderingStrategy<NavigablePage, PassthroughTree<NavigablePage>> getTreeRenderingStrategy() {
        if (renderingStrategy == null) {
            renderingStrategy = new PassthroughTreeRenderingStrategy<NavigablePage>();
        }

        return renderingStrategy;
    }

}
