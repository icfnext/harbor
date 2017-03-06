package com.icfolson.aem.harbor.core.content.page.navigation.construction;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * Extends the PageNavigationListConstructionStrategy forcing the root of the
 * Navigation to be the nearest harbor:HomePage type page.
 */
@Model(adaptables = Resource.class)
public class SiteNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

	private Optional<NavigablePage> navigationRoot;

    @Inject
    private PageDecorator currentPage;

	@Override
    @IgnoreDialogField
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			HierarchicalPage currentHierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

            navigationRoot = Optional.absent();

            if (currentHierarchicalPage != null) {
                Optional<HomePage> homePageOptional = currentHierarchicalPage.getHomePage();

                if (homePageOptional.isPresent()) {
                    navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
                        homePageOptional.get(), getNavigationDepth(), !isIgnoreHideInNavigation(), currentPage));
                }
            }
		}

		return navigationRoot;
	}
}
