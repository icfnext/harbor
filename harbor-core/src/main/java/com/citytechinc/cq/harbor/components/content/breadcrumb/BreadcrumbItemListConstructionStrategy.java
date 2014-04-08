package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.node.BasicNode;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbItemListConstructionStrategy implements ListConstructionStrategy<BreadcrumbItem> {

    private final static String ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "rootBreadcrumbPageOptions";
    private static final String INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "intermediaryBreadcrumbPageOptions";
    private static final String CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "currentBreadcrumbPageOptions";
    private final HierarchicalPage currentPage;
    private final ComponentNode currentComponentNode;
    @DialogField(ranking = 3)
    @DialogFieldSet(title = "Current Page Config", collapsible = true, collapsed = true, namePrefix = CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/")
    public BreadcrumbItemConfigNode currentBreadcrumbItemConfigNode;
    @DialogField(ranking = 4)
    @DialogFieldSet(title = "Intermediary Page Config", collapsible = true, collapsed = true, namePrefix = INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/")
    public BreadcrumbItemConfigNode intermediaryBreadcrumbItemConfigNode;
    @DialogField(ranking = 5)
    @DialogFieldSet(title = "Root Page Config", collapsible = true, collapsed = true, namePrefix = ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/")
    public BreadcrumbItemConfigNode rootBreadcrumbItemConfigNode;

    public BreadcrumbItemListConstructionStrategy(ComponentRequest request) {
        this.currentPage = request.getCurrentPage().adaptTo(HierarchicalPage.class);
        this.currentComponentNode = request.getComponentNode();
    }

    @Override
    public List<BreadcrumbItem> constructList() {
        List<BreadcrumbItem> breadcrumbPageList = new ArrayList<BreadcrumbItem>();
        Optional<? extends HierarchicalPage> rootPageOptional;
        String rootPageType = getRootPageType();

        if (rootPageType.equals(SectionLandingPage.RDF_TYPE)) {
            rootPageOptional = currentPage.getSectionLandingPage();
        } else if (rootPageType.equals(HomePage.RDF_TYPE)) {
            rootPageOptional = currentPage.getHomePage();
        } else {
            rootPageOptional = Optional.absent();
        }

        if (rootPageOptional.isPresent()) {
            String rootPagePath = rootPageOptional.get().getPath();
            HierarchicalPage currentTrailPage = currentPage;
            BreadcrumbItem currentPageBreadcrumbItem = new BreadcrumbItem(currentTrailPage, getCurrentBreadcrumbItemConfigNodeOptional());
            currentPageBreadcrumbItem.setIsCurrentPage(true);
            breadcrumbPageList.add(currentPageBreadcrumbItem);
            while (currentTrailPage != null && !currentTrailPage.getPath().equals(rootPagePath)) {
                currentTrailPage = currentTrailPage.getParent().adaptTo(HierarchicalPage.class);
                breadcrumbPageList.add(new BreadcrumbItem(currentTrailPage, getIntermediaryBreadcrumbItemConfigNodeOptional()));
            }
        }
        //The last item in the breadcrumb page is the "root page" and has additional configuration to account for.
        if (breadcrumbPageList.size() > 1) {
            int breadcrumbPageListLastItemIndex = breadcrumbPageList.size() - 1;
            BreadcrumbItem rootBreadcrumbItem = breadcrumbPageList.get(breadcrumbPageListLastItemIndex);
            rootBreadcrumbItem = new BreadcrumbItem(rootBreadcrumbItem.getPage(), getRootBreadcrumbItemConfigNodeOptional());
            rootBreadcrumbItem.setIsRoot(true);
            breadcrumbPageList.set(breadcrumbPageListLastItemIndex, rootBreadcrumbItem);
        }

        if (getHideCurrentPageInBreadcrumb()) {
            breadcrumbPageList.remove(0);
        }
        return Lists.reverse(breadcrumbPageList);
    }

    @DialogField(fieldLabel = "Root Page Type", fieldDescription = "The type of page that the breadcrumb will display as the root page.", ranking = 1)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE),
            @Option(text = "Home Page", value = HomePage.RDF_TYPE)
    })
    public String getRootPageType() {
        return currentComponentNode.get("rootPageType", SectionLandingPage.RDF_TYPE);
    }

    @DialogField(fieldLabel = "Hide current page in Breadcrumb?", ranking = 2)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
    public boolean getHideCurrentPageInBreadcrumb() {
        return currentComponentNode.get("hideCurrentPageInBreadcrumb", false);
    }

    private Optional<BreadcrumbItemConfigNode> getCurrentBreadcrumbItemConfigNodeOptional() {
        return Optional.fromNullable(getBreadcrumbItemConfigNode(CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH));
    }

    private Optional<BreadcrumbItemConfigNode> getIntermediaryBreadcrumbItemConfigNodeOptional() {
        return Optional.fromNullable(getBreadcrumbItemConfigNode(INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH));
    }

    private Optional<BreadcrumbItemConfigNode> getRootBreadcrumbItemConfigNodeOptional() {
        return Optional.fromNullable(getBreadcrumbItemConfigNode(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH));
    }

    private BreadcrumbItemConfigNode getBreadcrumbItemConfigNode(String nodeType) {
        Resource rootPageResource = currentComponentNode.getResource().getChild(nodeType);
        if (rootPageResource != null) {
            return new BreadcrumbItemConfigNode(rootPageResource.adaptTo(BasicNode.class));
        } else {
            return null;
        }
    }
}
