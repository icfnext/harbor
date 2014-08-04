package com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction;

import com.citytechinc.cq.accelerate.api.ontology.Types;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.proper.api.content.page.HomePage;
import com.citytechinc.cq.harbor.proper.api.content.page.SectionLandingPage;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.NavigablePages;
import com.google.common.base.Optional;

public class HierarchicalPageNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

	private Optional<NavigablePage> navigationRoot;

	@DialogField(fieldLabel = "Root Page Type", fieldDescription = "Select Home Page if this navigation should be rooted at the current site's Home Page and Section Landing Page if this navigation should be rooted at the nearest parent Section Landing Page")
	@Selection(type = Selection.SELECT, options = { @Option(text = "Home Page", value = Types.ACCELERATE_HOME_PAGE),
		@Option(text = "Section Landing Page", value = Types.ACCELERATE_SECTION_LANDING_PAGE) })
	@Override
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			HierarchicalPage currentHierarchicalPage = getCurrentPage().adaptTo(HierarchicalPage.class);
			String rootTypeSelection = get("navigationRoot", Types.ACCELERATE_HOME_PAGE);

			if (Types.ACCELERATE_SECTION_LANDING_PAGE.equals(rootTypeSelection)) {
				Optional<SectionLandingPage> sectionLandingPageOptional = currentHierarchicalPage
					.getSectionLandingPage();
				if (sectionLandingPageOptional.isPresent()) {
					navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
						sectionLandingPageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(),
						getCurrentPage()));
				} else {
					navigationRoot = Optional.absent();
				}
			} else {
				Optional<HomePage> homePageOptional = currentHierarchicalPage.getHomePage();
				if (homePageOptional.isPresent()) {
					navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
						homePageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(), getCurrentPage()));
				} else {
					navigationRoot = Optional.absent();
				}
			}
		}

		return navigationRoot;
	}

}
