package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.construction.RootedListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.RootedListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractRootedListComponent;

import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction.SiteNavigationListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation.BootstrapPageNavigableRenderableRoot;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.nio.charset.Charset;

@Component( value = "Main Auto Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        actions = {"text:Bootstrap Main Auto Navigation", "-", "edit", "-", "copymove", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.navigation.mainnavigation")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        name = "navigation/bootstrapmainautonavigation",
        allowedParents = "*/parsys"
)
@AutoInstantiate( instanceName = "bootstrapMainAutoNavigation" )
public class BootstrapMainAutoNavigation extends AbstractRootedListComponent<NavigablePage, BootstrapPageNavigableRenderableRoot> {

    private static HashFunction hashFunction = Hashing.md5();

    @DialogField(ranking = 1)
    @DialogFieldSet( title = "Page Navigation Construction" )
    private SiteNavigationListConstructionStrategy constructionStrategy;

    @DialogField(ranking = 10)
    @DialogFieldSet( title = "Page Navigation Rendering" )
    private BootstrapMainNavigationRenderingStrategy renderingStrategy;

    public BootstrapMainAutoNavigation(ComponentRequest request) {
        super(request);
    }

    @Override
    protected RootedListConstructionStrategy<NavigablePage> getListConstructionStrategy() {
        if (constructionStrategy == null) {
            constructionStrategy = new SiteNavigationListConstructionStrategy(request);
        }

        return constructionStrategy;
    }

    @Override
    protected RootedListRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableRoot> getListRenderingStrategy() {
        if (renderingStrategy == null) {
            renderingStrategy = new BootstrapMainNavigationRenderingStrategy(request);
        }

        return renderingStrategy;
    }

    public String getId() throws RepositoryException {
        return hashFunction.newHasher().putString(getResource().getPath(), Charset.defaultCharset()).hash().toString();
    }

}
