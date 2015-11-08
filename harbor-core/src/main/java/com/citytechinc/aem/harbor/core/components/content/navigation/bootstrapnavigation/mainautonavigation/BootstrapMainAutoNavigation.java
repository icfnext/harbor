package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.citytechinc.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.citytechinc.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.citytechinc.aem.harbor.core.components.content.list.AbstractRootedListComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.content.page.navigation.construction.SiteNavigationListConstructionStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
        value = "Main Auto Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        actions = {
	        "text:Main Auto Navigation", "-", "edit", "-", "copymove", "delete" },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.bootstrap.navbar,harbor.navigation.mainnavigation]") },
        name = "navigation/bootstrapmainautonavigation",
        allowedParents = "*/parsys")
@AutoInstantiate(instanceName = "bootstrapMainAutoNavigation")
@Model(adaptables = Resource.class)
public class BootstrapMainAutoNavigation extends
	AbstractRootedListComponent<NavigablePage, BootstrapPageNavigableRenderableTree> {

	public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainautonavigation";

	@Inject
	private PageDecorator currentPage;

	@DialogField(ranking = 1)
	@DialogFieldSet(title = "Page Navigation Construction")
	private SiteNavigationListConstructionStrategy constructionStrategy;

	@DialogField(ranking = 10)
	@DialogFieldSet(title = "Page Navigation Rendering")
	private BootstrapMainNavigationRenderingStrategy renderingStrategy;

	@Override
	protected TreeConstructionStrategy<NavigablePage> getTreeConstructionStrategy() {
		if (constructionStrategy == null) {
			constructionStrategy = this.getResource().adaptTo(SiteNavigationListConstructionStrategy.class);
		}

		return constructionStrategy;
	}

	@Override
	protected TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> getTreeRenderingStrategy() {
		if (renderingStrategy == null) {
			renderingStrategy = this.getResource().adaptTo(BootstrapMainNavigationRenderingStrategy.class);
		}

		return renderingStrategy;
	}

    public boolean isOnStructuralPage() {
        HierarchicalPage hierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

        if (hierarchicalPage != null) {
            return hierarchicalPage.isStructuralPage();
        }

        return false;
    }

}
