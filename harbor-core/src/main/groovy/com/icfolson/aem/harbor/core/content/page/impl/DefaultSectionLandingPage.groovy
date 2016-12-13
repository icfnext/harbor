package com.icfolson.aem.harbor.core.content.page.impl

import com.icfolson.aem.harbor.api.content.page.SectionLandingPage
import com.icfolson.aem.library.api.page.PageDecorator

class DefaultSectionLandingPage extends DefaultHierarchicalPage implements SectionLandingPage {

    DefaultSectionLandingPage(PageDecorator page) {
        super(page)
    }

    @Override
    boolean isSubSectionLandingPage() {
        return sectionLandingPage.isPresent()
    }

    @Override
    List<SectionLandingPage> getSectionLandingPages() {
        def sectionLandingPageList = pageManager.findPages(getPath(), PagePredicates.SECTION_LANDING_PAGE_PREDICATE)

        sectionLandingPageList.collect { page ->
            page.adaptTo(SectionLandingPage)
        }
    }
}
