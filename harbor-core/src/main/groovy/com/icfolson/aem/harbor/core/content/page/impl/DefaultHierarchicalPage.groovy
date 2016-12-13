package com.icfolson.aem.harbor.core.content.page.impl

import com.citytechinc.aem.namespace.api.ontology.Properties
import com.citytechinc.aem.namespace.api.ontology.Types
import com.google.common.base.Optional
import com.icfolson.aem.harbor.api.content.page.HomePage
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage
import com.icfolson.aem.harbor.core.content.page.AbstractHierarchicalPage
import com.icfolson.aem.library.api.page.PageDecorator

import javax.jcr.Node as JcrNode

class DefaultHierarchicalPage extends AbstractHierarchicalPage {

    private Optional<SectionLandingPage> sectionLandingPage
    private Optional<HomePage> homePage

    DefaultHierarchicalPage(final PageDecorator page) {
        super(page)
    }

    @Override
    Optional<SectionLandingPage> getSectionLandingPage() {
        if (this.sectionLandingPage != null) {
            return this.sectionLandingPage
        }

        Optional<PageDecorator> sectionLandingPageOptional = findAncestor(PagePredicates.SECTION_LANDING_PAGE_PREDICATE)


        if (sectionLandingPageOptional.isPresent()) {
            this.sectionLandingPage = Optional.fromNullable(sectionLandingPageOptional.get().adaptTo(
                SectionLandingPage.class))
        } else {
            this.sectionLandingPage = Optional.absent()
        }

        return this.sectionLandingPage
    }

    @Override
    Optional<HomePage> getHomePage() {
        if (this.homePage != null) {
            return this.homePage
        }

        Optional<PageDecorator> homePageOptional = findAncestor(PagePredicates.HOME_PAGE_PREDICATE)

        if (homePageOptional.isPresent()) {
            this.homePage = Optional.fromNullable(homePageOptional.get().adaptTo(HomePage.class))
        } else {
            this.homePage = Optional.absent()
        }

        return this.homePage
    }

    /*
     * TODO: in backlog
     *
     * @Override public String getPageIconImage() { return
     * this.getImageSource(Properties.PAGE_ICON_IMAGE).or(""); }
     */

    @Override
    String getPageIcon() {
        getProperties().get(Properties.CITYTECH_ICONIC_REPRESENTATION, "")
    }

    @Override
    boolean isStructuralPage() {
        contentResource.adaptTo(JcrNode).isNodeType(Types.CITYTECH_STRUCTURAL_PAGE)
    }
}