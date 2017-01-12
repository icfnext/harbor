package com.icfolson.aem.harbor.core.content.page.impl

import com.google.common.base.Function
import com.google.common.base.Optional
import com.icfolson.aem.harbor.api.content.page.HomePage
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage
import com.icfolson.aem.harbor.core.content.page.AbstractHierarchicalPage
import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.namespace.api.ontology.Properties
import com.icfolson.aem.namespace.api.ontology.Types

import javax.jcr.Node as JcrNode

import static com.icfolson.aem.harbor.core.content.page.impl.PagePredicates.HOME_PAGE_PREDICATE
import static com.icfolson.aem.harbor.core.content.page.impl.PagePredicates.SECTION_LANDING_PAGE_PREDICATE

class DefaultHierarchicalPage extends AbstractHierarchicalPage {

    private Optional<SectionLandingPage> sectionLandingPage

    private Optional<HomePage> homePage

    DefaultHierarchicalPage(final PageDecorator page) {
        super(page)
    }

    @Override
    Optional<SectionLandingPage> getSectionLandingPage() {
        if (sectionLandingPage == null) {
            sectionLandingPage = findAncestor(SECTION_LANDING_PAGE_PREDICATE).transform(
                new Function<PageDecorator, SectionLandingPage>() {
                    @Override
                    SectionLandingPage apply(PageDecorator page) {
                        page.adaptTo(SectionLandingPage)
                    }
                })
        }

        sectionLandingPage
    }

    @Override
    Optional<HomePage> getHomePage() {
        if (homePage == null) {
            homePage = findAncestor(HOME_PAGE_PREDICATE).transform(new Function<PageDecorator, HomePage>() {
                @Override
                HomePage apply(PageDecorator page) {
                    page.adaptTo(HomePage)
                }
            })
        }

        homePage
    }

    /*
     * TODO: in backlog
     *
     * @Override public String getPageIconImage() { return
     * this.getImageSource(Properties.PAGE_ICON_IMAGE).or(""); }
     */

    @Override
    String getPageIcon() {
        properties.get(Properties.ICF_OLSON_ICONIC_REPRESENTATION, "")
    }

    @Override
    boolean isStructuralPage() {
        contentResource.adaptTo(JcrNode).isNodeType(Types.ICF_OLSON_STRUCTURAL_PAGE)
    }
}