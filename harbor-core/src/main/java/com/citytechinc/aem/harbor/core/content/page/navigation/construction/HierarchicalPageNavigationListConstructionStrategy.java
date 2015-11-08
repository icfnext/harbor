package com.citytechinc.aem.harbor.core.content.page.navigation.construction;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.namespace.api.ontology.Types;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.content.page.HierarchicalPage;
import com.citytechinc.aem.harbor.api.content.page.HomePage;
import com.citytechinc.aem.harbor.api.content.page.SectionLandingPage;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.citytechinc.aem.harbor.core.content.page.navigation.NavigablePages;
import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class HierarchicalPageNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

	private Optional<NavigablePage> navigationRoot;

	@Inject
	private PageDecorator currentPage;

	@DialogField(fieldLabel = "Root Page Type", fieldDescription = "Select Home Page if this navigation should be rooted at the current site's Home Page and Section Landing Page if this navigation should be rooted at the nearest parent Section Landing Page")
	@Selection(type = Selection.SELECT, options = { @Option(text = "Home Page", value = Types.CITYTECH_HOME_PAGE),
		@Option(text = "Section Landing Page", value = Types.CITYTECH_SECTION_LANDING_PAGE) })
	@Override
	public Optional<NavigablePage> getNavigationRoot() {
		if (navigationRoot == null) {
			HierarchicalPage currentHierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);
			String rootTypeSelection = getInherited("navigationRoot", Types.CITYTECH_HOME_PAGE);

			if (Types.CITYTECH_SECTION_LANDING_PAGE.equals(rootTypeSelection)) {
				Optional<SectionLandingPage> sectionLandingPageOptional = currentHierarchicalPage
					.getSectionLandingPage();
				if (sectionLandingPageOptional.isPresent()) {
					navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
						sectionLandingPageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(),
						currentPage));
				} else {
					navigationRoot = Optional.absent();
				}
			} else {
				Optional<HomePage> homePageOptional = currentHierarchicalPage.getHomePage();
				if (homePageOptional.isPresent()) {
					navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
						homePageOptional.get(), getNavigationDepth(), !getIgnoreHideInNavigation(), currentPage));
				} else {
					navigationRoot = Optional.absent();
				}
			}
		}

		return navigationRoot;
	}

}
