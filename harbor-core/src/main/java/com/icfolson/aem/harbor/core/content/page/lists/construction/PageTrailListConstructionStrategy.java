package com.icfolson.aem.harbor.core.content.page.lists.construction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.core.components.content.page.TrailPage;
import com.icfolson.aem.harbor.core.content.page.impl.PagePredicates;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class PageTrailListConstructionStrategy extends AbstractComponent implements ListConstructionStrategy<TrailPage> {

    private List<TrailPage> constructedList;

    @Inject
    private PageDecorator currentPage;

    @DialogField(name = "./rootPageType", fieldLabel = "Breadcrumb Root")
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Home Page", value = HomePage.RDF_TYPE),
        @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE)
    })
    private Predicate<PageDecorator> rootPagePredicate;

    @DialogField(fieldLabel = "Include Current Page In Trail")
    @Switch(offText = "No", onText = "Yes")
    private Boolean includeCurrentPageInTrail;

    @DialogField(fieldLabel = "Include Root Page In Trail")
    @Switch(offText = "No", onText = "Yes")
    private Boolean includeRootPageInTrail;

    @Override
    public List<TrailPage> construct() {
        if (constructedList == null) {
            List<TrailPage> listUnderConstruction = Lists.newArrayList();

            PageDecorator currentPageInTrail = currentPage;

            boolean finished = false;

            if (isIncludeCurrentPageInTrail()) {
                if (isPageRoot(currentPageInTrail)) {
                    listUnderConstruction.add(TrailPage.forRootCurrentPage(currentPageInTrail));
                    finished = true;
                } else {
                    listUnderConstruction.add(TrailPage.forCurrentPage(currentPageInTrail));
                }
            }

            if (!finished) {
                currentPageInTrail = currentPageInTrail.getParent();

                while (currentPageInTrail != null && !isPageRoot(currentPageInTrail)) {
                    listUnderConstruction.add(TrailPage.forPage(currentPageInTrail));
                    currentPageInTrail = currentPageInTrail.getParent();
                }

                if (currentPageInTrail != null && isIncludeRootPageInTrail()) {
                    listUnderConstruction.add(TrailPage.forRootPage(currentPageInTrail));
                }
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
            final String rootPageType = isInherits() ? getInherited("rootPageType", HomePage.RDF_TYPE) : get(
                "rootPageType", HomePage.RDF_TYPE);

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
            includeCurrentPageInTrail = isInherits() ? getInherited("includeCurrentPageInTrail", false) : get(
                "includeCurrentPageInTrail", false);
        }

        return includeCurrentPageInTrail;
    }

    protected Boolean isIncludeRootPageInTrail() {
        if (includeRootPageInTrail == null) {
            includeRootPageInTrail = isInherits() ? getInherited("includeRootPageInTrail", false) : get(
                "includeRootPageInTrail", false);
        }

        return includeRootPageInTrail;
    }

    protected Boolean isPageRoot(PageDecorator pageDecorator) {
        return getRootPagePredicate().apply(pageDecorator);
    }

    protected boolean isInherits() {
        return false;
    }
}
