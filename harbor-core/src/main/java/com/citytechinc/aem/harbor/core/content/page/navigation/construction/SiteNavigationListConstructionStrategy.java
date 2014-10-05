package com.citytechinc.aem.harbor.core.content.page.navigation.construction;

import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage;
import com.citytechinc.aem.harbor.api.content.page.HomePage;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.citytechinc.aem.harbor.core.content.page.navigation.NavigablePages;
import com.google.common.base.Optional;

/**
 * Extends the PageNavigationListConstructionStrategy forcing the root of the
 * Navigation to be the nearest harbor:HomePage type page.
 */
public class SiteNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

	private Optional<NavigablePage> navigationRoot;

	@Override
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			HierarchicalPage currentHierarchicalPage = getCurrentPage().adaptTo(HierarchicalPage.class);
			Optional<HomePage> homePageOptional = currentHierarchicalPage.getHomePage();

			if (homePageOptional.isPresent()) {
				navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
					homePageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(), getCurrentPage()));
			} else {
				navigationRoot = Optional.absent();
			}
		}

		return navigationRoot;
	}
}
