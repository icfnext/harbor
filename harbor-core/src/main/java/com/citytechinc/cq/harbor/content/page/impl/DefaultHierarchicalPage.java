package com.citytechinc.cq.harbor.content.page.impl;


import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

public class DefaultHierarchicalPage extends AbstractHierarchicalPage implements HierarchicalPage {

    private Optional<SectionLandingPage> sectionLandingPage;
    private Optional<HomePage> homePage;

    public DefaultHierarchicalPage(final PageDecorator page) {
        super(page);
    }

    @Override
    public Optional<SectionLandingPage> getSectionLandingPage() {
        if (this.sectionLandingPage != null) {
            return this.sectionLandingPage;
        }

        Optional<PageDecorator> sectionLandingPageOptional = findAncestor(PagePredicates.SECTION_LANDING_PAGE_PREDICATE);

        if (sectionLandingPageOptional.isPresent()) {
            this.sectionLandingPage = Optional.fromNullable(sectionLandingPageOptional.get().adaptTo(SectionLandingPage.class));
        }
        else {
            this.sectionLandingPage = Optional.absent();
        }

        return this.sectionLandingPage;
    }

    @Override
    public Optional<HomePage> getHomePage() {
        if (this.homePage != null) {
            return this.homePage;
        }

        Optional<PageDecorator> homePageOptional = findAncestor(PagePredicates.HOME_PAGE_PREDICATE);

        if (homePageOptional.isPresent()) {
            this.homePage = Optional.fromNullable(homePageOptional.get().adaptTo(HomePage.class));
        }
        else {
            this.homePage = Optional.absent();
        }

        return this.homePage;
    }

    //TODO: implement these methods
    @Override
    public Optional<String> getPageIconImage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Optional<String> getPageIcon() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
