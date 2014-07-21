package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.secondarynavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.RootedListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractRootedListComponent;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction.HierarchicalPageNavigationListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.lists.rendering.PassthroughRootedListRenderingStrategy;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Bootstrap Secondary Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        name = "navigation/bootstrapsecondarynavigation",
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="harbor.bootstrap.navs")},
        allowedParents = "*/parsys"
)
@AutoInstantiate( instanceName = "secondaryNavigation" )
public class BootstrapSecondaryNavigation extends AbstractRootedListComponent<NavigablePage, NavigablePage> {

    @DialogField
    @DialogFieldSet(title = "Secondary Navigation Construction")
    private HierarchicalPageNavigationListConstructionStrategy constructionStrategy;
    private RootedListRenderingStrategy<NavigablePage, NavigablePage> renderingStrategy;

    public BootstrapSecondaryNavigation(ComponentRequest request) {
        super(request);
    }

    @Override
    public HierarchicalPageNavigationListConstructionStrategy getListConstructionStrategy() {
        if (constructionStrategy == null) {
            constructionStrategy = new HierarchicalPageNavigationListConstructionStrategy(request);
        }

        return constructionStrategy;
    }

    @Override
    protected RootedListRenderingStrategy<NavigablePage, NavigablePage> getListRenderingStrategy() {
        if (renderingStrategy == null) {
            renderingStrategy = new PassthroughRootedListRenderingStrategy<NavigablePage>();
        }

        return renderingStrategy;
    }

}
