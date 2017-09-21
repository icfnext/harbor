package com.icfolson.aem.harbor.core.content.page.v1

import com.icfolson.aem.harbor.api.content.page.SectionLandingPage

class DefaultSectionLandingPage extends DefaultHierarchicalPage implements SectionLandingPage {

    @Override
    boolean isSubSectionLandingPage() {
        sectionLandingPage.present
    }

    @Override
    List<SectionLandingPage> getSectionLandingPages() {
        pageManager.findPages(path, PagePredicates.SECTION_LANDING_PAGE_PREDICATE)*.adaptTo(SectionLandingPage)
    }

    @Override
    boolean isHomePage() {
        return false
    }

    @Override
    boolean isSectionLandingPage() {
        return true
    }
}
