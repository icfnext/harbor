package com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction;

import com.citytechinc.cq.harbor.proper.api.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.proper.api.content.page.HomePage;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.NavigablePages;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;

/**
 * Extends the PageNavigationListConstructionStrategy forcing the root of the Navigation to be
 * the nearest harbor:HomePage type page.
 */
public class SiteNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

    private Optional<NavigablePage> navigationRoot;

    public SiteNavigationListConstructionStrategy(ComponentRequest request) {
        super(request);
    }

    @Override
    public Optional<NavigablePage> getNavigationRoot() {
        if (navigationRoot == null) {
            HierarchicalPage currentHierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);
            Optional<HomePage> homePageOptional = currentHierarchicalPage.getHomePage();

            if (homePageOptional.isPresent()) {
                navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(homePageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(), currentPage));
            }
            else {
                navigationRoot = Optional.absent();
            }
        }

        return navigationRoot;
    }
}
