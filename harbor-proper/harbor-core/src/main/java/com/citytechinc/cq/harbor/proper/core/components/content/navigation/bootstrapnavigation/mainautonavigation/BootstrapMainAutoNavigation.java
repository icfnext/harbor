package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.trees.construction.TreeConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.trees.rendering.TreeRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractRootedListComponent;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction.SiteNavigationListConstructionStrategy;

@Component(
        value = "Main Auto Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        actions = {
	        "text:Main Auto Navigation", "-", "edit", "-", "copymove", "delete" },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.bootstrap.navbar,harbor.navigation.mainnavigation]") },
        listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") },
        name = "navigation/bootstrapmainautonavigation",
        allowedParents = "*/parsys")
@AutoInstantiate(instanceName = "bootstrapMainAutoNavigation")
public class BootstrapMainAutoNavigation extends
	AbstractRootedListComponent<NavigablePage, BootstrapPageNavigableRenderableTree> {

	@DialogField(ranking = 1)
	@DialogFieldSet(title = "Page Navigation Construction")
	private SiteNavigationListConstructionStrategy constructionStrategy;

	@DialogField(ranking = 10)
	@DialogFieldSet(title = "Page Navigation Rendering")
	private BootstrapMainNavigationRenderingStrategy renderingStrategy;

	@Override
	protected TreeConstructionStrategy<NavigablePage> getTreeConstructionStrategy() {
		if (constructionStrategy == null) {
			constructionStrategy = getComponent(this, SiteNavigationListConstructionStrategy.class).get();
		}

		return constructionStrategy;
	}

	@Override
	protected TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> getTreeRenderingStrategy() {
		if (renderingStrategy == null) {
			renderingStrategy = getComponent(this, BootstrapMainNavigationRenderingStrategy.class).get();
		}

		return renderingStrategy;
	}

}
