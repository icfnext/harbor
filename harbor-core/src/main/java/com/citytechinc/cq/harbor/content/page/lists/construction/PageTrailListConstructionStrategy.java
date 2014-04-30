package com.citytechinc.cq.harbor.content.page.lists.construction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.breadcrumb.BreadcrumbPage;
import com.citytechinc.cq.harbor.content.page.impl.PagePredicates;
import com.citytechinc.cq.harbor.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.List;

public class PageTrailListConstructionStrategy implements ListConstructionStrategy<BreadcrumbPage> {

    private final ComponentRequest request;

    private List<BreadcrumbPage> constructedList;

    private PageDecorator currentPage;

    @DialogField( name = "./rootPageType", fieldLabel = "Breadcrumb Root" )
    @Selection( type = Selection.SELECT, options = {
        @Option( text = "Home Page", value = HomePage.RDF_TYPE ),
        @Option( text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE )
    } )
    private Predicate<PageDecorator> rootPagePredicate;

    @DialogField( fieldLabel = "Include Current Page In Trail" )
    @Selection( type = Selection.CHECKBOX, options = {
        @Option( text = "Yes", value = "true" )
    } )
    private Boolean includeCurrentPageInTrail;

    @DialogField( fieldLabel = "Include Root Page In Trail" )
    @Selection( type = Selection.CHECKBOX, options = {
        @Option( text = "Yes", value = "true" )
    } )
    private Boolean includeRootPageInTrail;

    public PageTrailListConstructionStrategy(ComponentRequest request) {
        this.request = request;
    }

    @Override
    public List<BreadcrumbPage> construct() {

        if (constructedList == null) {

            List<BreadcrumbPage> listUnderConstruction = Lists.newArrayList();

            PageDecorator currentPageInTrail = getCurrentPage();

            if (isIncludeCurrentPageInTrail()) {

                if (getRootPagePredicate().apply(currentPageInTrail)) {
                    listUnderConstruction.add(BreadcrumbPage.forRootCurrentPage(currentPageInTrail));
                }
                else {
                    listUnderConstruction.add(BreadcrumbPage.forRootCurrentPage(currentPageInTrail));
                }

            }

            currentPageInTrail = currentPageInTrail.getParent();

            while (currentPageInTrail != null && !isPageRoot(currentPageInTrail)) {
                listUnderConstruction.add(BreadcrumbPage.forCurrentPage(currentPageInTrail));
                currentPageInTrail = currentPageInTrail.getParent();
            }

            if (currentPageInTrail != null && isIncludeRootPageInTrail()) {
                listUnderConstruction.add(BreadcrumbPage.forRootPage(currentPageInTrail));
            }

            constructedList = Lists.reverse(listUnderConstruction);

        }

        return constructedList;

    }

    protected PageDecorator getCurrentPage() {
        if (currentPage == null) {
            currentPage = request.getCurrentPage();
        }

        return currentPage;
    }

    protected Predicate<PageDecorator> getRootPagePredicate() {
        if (rootPagePredicate == null) {
            String rootPageType = request.getComponentNode().get("rootPageType", HomePage.RDF_TYPE);

            if (SectionLandingPage.RDF_TYPE.equals(rootPageType)) {
                rootPagePredicate = PagePredicates.SECTION_LANDING_PAGE_PREDICATE;
            }
            else {
                rootPagePredicate = PagePredicates.HOME_PAGE_PREDICATE;
            }
        }

        return rootPagePredicate;
    }

    protected Boolean isIncludeCurrentPageInTrail() {
        if (includeCurrentPageInTrail == null) {
            includeCurrentPageInTrail = request.getComponentNode().get("includeCurrentPageInTrail", false);
        }

        return includeCurrentPageInTrail;
    }

    protected Boolean isIncludeRootPageInTrail() {
        if (includeRootPageInTrail == null) {
            includeRootPageInTrail = request.getComponentNode().get("includeRootPageInTrail", false);
        }

        return includeRootPageInTrail;
    }

    protected Boolean isPageRoot(PageDecorator pageDecorator) {
        return getRootPagePredicate().apply(pageDecorator);
    }

}
