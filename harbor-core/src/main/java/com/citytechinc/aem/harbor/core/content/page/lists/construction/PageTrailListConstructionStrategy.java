package com.citytechinc.aem.harbor.core.content.page.lists.construction;

import java.util.List;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.content.page.HomePage;
import com.citytechinc.aem.harbor.api.content.page.SectionLandingPage;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.aem.harbor.core.components.content.page.TrailPage;
import com.citytechinc.aem.harbor.core.content.page.impl.PagePredicates;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class PageTrailListConstructionStrategy extends AbstractComponent implements ListConstructionStrategy<TrailPage> {

	private List<TrailPage> constructedList;

	@Inject
	private PageDecorator currentPage;

	@DialogField(name = "./rootPageType", fieldLabel = "Breadcrumb Root")
	@Selection(type = Selection.SELECT, options = { @Option(text = "Home Page", value = HomePage.RDF_TYPE),
		@Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE) })
	private Predicate<PageDecorator> rootPagePredicate;

	@DialogField(fieldLabel = "Include Current Page In Trail")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "Yes", value = "true") })
	private Boolean includeCurrentPageInTrail;

	@DialogField(fieldLabel = "Include Root Page In Trail")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "Yes", value = "true") })
	private Boolean includeRootPageInTrail;

	@Override
	public List<TrailPage> construct() {

		if (constructedList == null) {

			List<TrailPage> listUnderConstruction = Lists.newArrayList();

			PageDecorator currentPageInTrail = getCurrentPage();

			if (isIncludeCurrentPageInTrail()) {

				//TODO: Cleanup.  The short circuit here feels messy
				if (isPageRoot(currentPageInTrail)) {
					listUnderConstruction.add(TrailPage.forRootCurrentPage(currentPageInTrail));
                    constructedList = listUnderConstruction;
                    return constructedList;
				} else {
					listUnderConstruction.add(TrailPage.forCurrentPage(currentPageInTrail));
				}

			}

			currentPageInTrail = currentPageInTrail.getParent();

			while (currentPageInTrail != null && !isPageRoot(currentPageInTrail)) {
				listUnderConstruction.add(TrailPage.forCurrentPage(currentPageInTrail));
				currentPageInTrail = currentPageInTrail.getParent();
			}

			if (currentPageInTrail != null && isIncludeRootPageInTrail()) {
				listUnderConstruction.add(TrailPage.forRootPage(currentPageInTrail));
			}

			constructedList = Lists.reverse(listUnderConstruction);

		}

		return constructedList;

	}

	protected PageDecorator getCurrentPage() {
		return currentPage;
	}

	protected Predicate<PageDecorator> getRootPagePredicate() {
		if (rootPagePredicate == null) {
			String rootPageType = getInherited("rootPageType", HomePage.RDF_TYPE);

			if (SectionLandingPage.RDF_TYPE.equals(rootPageType)) {
				rootPagePredicate = PagePredicates.SECTION_LANDING_PAGE_PREDICATE;
			} else {
				rootPagePredicate = PagePredicates.HOME_PAGE_PREDICATE;
			}
		}

		return rootPagePredicate;
	}

	protected Boolean isIncludeCurrentPageInTrail() {
		if (includeCurrentPageInTrail == null) {
			includeCurrentPageInTrail = getInherited("includeCurrentPageInTrail", false);
		}

		return includeCurrentPageInTrail;
	}

	protected Boolean isIncludeRootPageInTrail() {
		if (includeRootPageInTrail == null) {
			includeRootPageInTrail = getInherited("includeRootPageInTrail", false);
		}

		return includeRootPageInTrail;
	}

	protected Boolean isPageRoot(PageDecorator pageDecorator) {
		return getRootPagePredicate().apply(pageDecorator);
	}

}
