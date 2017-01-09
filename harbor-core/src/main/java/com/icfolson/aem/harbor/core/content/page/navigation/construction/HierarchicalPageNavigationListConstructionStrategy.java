package com.icfolson.aem.harbor.core.content.page.navigation.construction;

import com.citytechinc.aem.namespace.api.ontology.Types;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class HierarchicalPageNavigationListConstructionStrategy extends PageNavigationListConstructionStrategy {

    private Optional<NavigablePage> navigationRoot;

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Root Page Type",
        fieldDescription = "Select Home Page if this navigation should be rooted at the current site's Home Page and Section Landing Page if this navigation should be rooted at the nearest parent Section Landing Page")
    @Selection(type = Selection.SELECT, options = { @Option(text = "Home Page", value = Types.CITYTECH_HOME_PAGE),
        @Option(text = "Section Landing Page", value = Types.CITYTECH_SECTION_LANDING_PAGE) })
    @Override
    public Optional<NavigablePage> getNavigationRoot() {
        if (navigationRoot == null) {
            final HierarchicalPage currentHierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

            final String rootTypeSelection = getInherited("navigationRoot", Types.CITYTECH_HOME_PAGE);
            final Optional<? extends PageDecorator> rootPageOptional;

            if (Types.CITYTECH_SECTION_LANDING_PAGE.equals(rootTypeSelection)) {
                rootPageOptional = currentHierarchicalPage.getSectionLandingPage();
            } else {
                rootPageOptional = currentHierarchicalPage.getHomePage();
            }

            navigationRoot = rootPageOptional.transform(rootPage ->
                NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(rootPage, getNavigationDepth(),
                    !isIgnoreHideInNavigation(), currentPage));
        }

        return navigationRoot;
    }
}
