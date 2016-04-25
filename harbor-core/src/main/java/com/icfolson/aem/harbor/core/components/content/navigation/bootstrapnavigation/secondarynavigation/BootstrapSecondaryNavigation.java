package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.secondarynavigation;

import com.icfolson.aem.harbor.core.components.content.list.AbstractRootedListComponent;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.content.page.navigation.construction.HierarchicalPageNavigationListConstructionStrategy;
import com.icfolson.aem.harbor.core.trees.rendering.PassthroughTree;
import com.icfolson.aem.harbor.core.trees.rendering.PassthroughTreeRenderingStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
        value = "Secondary Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        path = "content/navigation",
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "harbor.bootstrap.navs") },
        allowedParents = "*/parsys" )
@AutoInstantiate(instanceName = "secondaryNavigation")
@Model(adaptables = Resource.class)
public class BootstrapSecondaryNavigation extends AbstractRootedListComponent<NavigablePage, PassthroughTree<NavigablePage>> {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapsecondarynavigation";

	@DialogField
	@DialogFieldSet(title = "Secondary Navigation Construction")
	private HierarchicalPageNavigationListConstructionStrategy constructionStrategy;
	private TreeRenderingStrategy<NavigablePage, PassthroughTree<NavigablePage>> renderingStrategy;

	@Override
	public HierarchicalPageNavigationListConstructionStrategy getTreeConstructionStrategy() {
		if (constructionStrategy == null) {
			constructionStrategy = this.getResource().adaptTo(HierarchicalPageNavigationListConstructionStrategy.class);
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
