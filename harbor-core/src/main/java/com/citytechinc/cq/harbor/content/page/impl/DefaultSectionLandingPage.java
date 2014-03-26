package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

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
        List<PageDecorator> sectionLandingPageList = getPageManager().findPages(getPath(), PagePredicates.SECTION_LANDING_PAGE_PREDICATE);
        List<SectionLandingPage> sectionLandingPages = new ArrayList<SectionLandingPage>();
        for(PageDecorator currentPageDecorator : sectionLandingPageList){
            sectionLandingPages.add(currentPageDecorator.adaptTo(SectionLandingPage.class));
        }
        return sectionLandingPages;
    }
}
