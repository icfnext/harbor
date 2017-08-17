package com.icfolson.aem.harbor.core.components.content.navigation.sitenavigation.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.components.content.navigation.sitenavigation.SiteNavigation;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTree;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTreeNode;
import com.icfolson.aem.harbor.core.model.page.pagetree.v1.AbstractPageTree;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Component(value = "Site Navigation (v1)",
    group = "Harbor Hidden",
    name = "navigation/sitenavigation/v1/sitenavigation")
@Model(adaptables = Resource.class, adapters = PageTree.class, resourceType = DefaultSiteNavigation.RESOURCE_TYPE)
public class DefaultSiteNavigation extends AbstractPageTree<PageTreeNode> implements SiteNavigation {

    @Inject
    private PageDecorator currentPage;

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/sitenavigation/v1/sitenavigation";

    public static final Predicate<PageDecorator> ALL_NON_HIDDEN_PAGES_PREDICATE =
            pageDecorator -> pageDecorator != null && !pageDecorator.isHideInNav();

    @Override
    public Optional<Integer> getDepth() {
        return Optional.empty();
    }

    @Override
    public Predicate<PageDecorator> getInclusionPredicate() {
        return ALL_NON_HIDDEN_PAGES_PREDICATE;
    }

    @Override
    public PageTreeNode transformPageAndChildren(PageDecorator page, List<PageTreeNode> children) {
        return new DefaultSiteNavigationNode(page, children);
    }

    @Override
    public PageDecorator getRootPage() {
        return currentPage;
    }

}
