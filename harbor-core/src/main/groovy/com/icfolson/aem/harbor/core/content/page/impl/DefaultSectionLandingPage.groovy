package com.icfolson.aem.harbor.core.content.page.impl;

import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage

public class DefaultSectionLandingPage extends DefaultHierarchicalPage implements SectionLandingPage {

	public DefaultSectionLandingPage(PageDecorator page) {
		super(page);
	}

	@Override
	public boolean isSubSectionLandingPage() {
		return getSectionLandingPage().isPresent();
	}

	@Override
	public List<SectionLandingPage> getSectionLandingPages() {
		List<PageDecorator> sectionLandingPageList = getPageManager().findPages(getPath(),
				PagePredicates.SECTION_LANDING_PAGE_PREDICATE);
		List<SectionLandingPage> sectionLandingPages = new ArrayList<SectionLandingPage>();
		for (PageDecorator currentPageDecorator : sectionLandingPageList) {
			sectionLandingPages.add(currentPageDecorator.adaptTo(SectionLandingPage.class));
		}
		return sectionLandingPages;
	}
}
