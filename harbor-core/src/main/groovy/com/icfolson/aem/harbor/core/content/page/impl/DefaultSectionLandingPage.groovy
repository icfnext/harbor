package com.icfolson.aem.harbor.core.content.page.impl

import com.icfolson.aem.harbor.api.content.page.SectionLandingPage
import com.icfolson.aem.library.api.page.PageDecorator

class DefaultSectionLandingPage extends DefaultHierarchicalPage implements SectionLandingPage {

    DefaultSectionLandingPage(PageDecorator page) {
        super(page)
    }

    @Override
    boolean isSubSectionLandingPage() {
        sectionLandingPage.present
    }

    @Override
    List<SectionLandingPage> getSectionLandingPages() {
        pageManager.findPages(path, PagePredicates.SECTION_LANDING_PAGE_PREDICATE)*.adaptTo(SectionLandingPage)
    }
}
