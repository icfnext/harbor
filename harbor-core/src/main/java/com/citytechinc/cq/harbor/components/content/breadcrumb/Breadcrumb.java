package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.components.ComponentConstants;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.BasicNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component(value = "Breadcrumb",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.bootstrap]"),
        })
public class Breadcrumb extends AbstractComponent {

    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";
    private final static String ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "rootBreadcrumbPageOptions";
    private static final String INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "intermediaryBreadcrumbPageOptions";
    private static final String CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH = "currentBreadcrumbPageOptions";
    private final String ICON_DELIMITER_HTML = "<i class=\"fa %s\"></i>";
    private List<BreadcrumbItem> trail;

    public Breadcrumb(final ComponentRequest request) {
        super(request);
    }

    /**
     * Returns a List of {@link BreadcrumbItem}, sorted in descending order.
     *
     * @return A descending trail of {@link BreadcrumbItem}.
     */
    public List<BreadcrumbItem> getTrail() {
        if (trail != null) {
            return trail;
        }

        List<PageDecorator> trailAsPageDecorators = Lists.newArrayList();
        HierarchicalPage currentHierarchicalPage = this.currentPage.adaptTo(HierarchicalPage.class);

        Optional<? extends HierarchicalPage> rootPage;
        String rootPageType = getRootPageType();
        if (rootPageType.equals(SectionLandingPage.RDF_TYPE)) {
            rootPage = currentHierarchicalPage.getSectionLandingPage();
        } else if (rootPageType.equals(HomePage.RDF_TYPE)) {
            rootPage = currentHierarchicalPage.getHomePage();
        } else {
            rootPage = Optional.absent();
        }

        if (rootPage.isPresent()) {
            PageDecorator currentTrailPage = currentPage;
            trailAsPageDecorators.add(currentTrailPage);

            while (currentTrailPage != null && !currentTrailPage.getPath().equals(rootPage.get().getPath())) {

                currentTrailPage = currentTrailPage.getParent();

                if (!currentTrailPage.isHideInNav()) {
                    trailAsPageDecorators.add(currentTrailPage);
                }
            }
        }
        trailAsPageDecorators = Lists.reverse(trailAsPageDecorators);
        return formatListForBreadcrumb(trailAsPageDecorators);
    }

    /**
     * Returns a list of PageDecorators to be rendered a Breadcrumb, using the {@link BreadcrumbItem} object.
     * The first element of this List is going to be considered the "rootPage." The last element will be considered the "current page".
     *
     * @param trailAsPageDecorators A trail of page decorators, already sorted in the order in which you want them to be displayed.
     * @return An unordered list of {@link BreadcrumbItem}.
     */
    public List<BreadcrumbItem> formatListForBreadcrumb(List<PageDecorator> trailAsPageDecorators) {
        //The first, intermediate, and root pages all have special behavior. Instead of making the JSP worry about that, we're going to go ahead and do it here.
        //Here we use Lists.newArrayList in order to make the list mutable.
        ListIterator<PageDecorator> trailAsPageDecoratorsIterator = trailAsPageDecorators.listIterator();
        List<BreadcrumbItem> newBreadcrumbItemList = new ArrayList<BreadcrumbItem>();
        HierarchicalPage currentPage;
        //We know root is going to be the first object we iterate over. So instead of doing a check, we just set a flag.
        boolean isRoot = true;
        while (trailAsPageDecoratorsIterator.hasNext()) {
            currentPage = trailAsPageDecoratorsIterator.next().adaptTo(HierarchicalPage.class);
            if (isRoot) {
                newBreadcrumbItemList.add(new BreadcrumbItem(currentPage, Optional.fromNullable(getRootBreadcrumbItemConfigNode())));
            } else if (trailAsPageDecoratorsIterator.previousIndex() == trailAsPageDecorators.size() - 1) {
                newBreadcrumbItemList.add(new BreadcrumbItem(currentPage, Optional.fromNullable(getCurrentBreadcrumbItemConfigNode())));
            } else {
                newBreadcrumbItemList.add(new BreadcrumbItem(currentPage, Optional.fromNullable(getIntermediaryBreadcrumbItemConfigNode())));
            }
            trailAsPageDecoratorsIterator.set(currentPage);
            isRoot = false;
        }


        // If we're to hide the current page in the breadcrumb, we simply remove it from the list.
        if (getHideCurrentPageInBreadcrumb()) {
            newBreadcrumbItemList.remove(newBreadcrumbItemList.size() - 1);
        }

        trail = newBreadcrumbItemList;

        return trail;
    }

    /**
     * A dialog field which allows the user to choose what delimiter icon they wish to be displayed.
     *
     * @return a string representing the font awesome icon class the user has selected.
     */
    @DialogField(fieldLabel = "Delimiter Icon", ranking = 2)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getIconDelimiter() {
        return get("iconDelimiter", DEFAULT_DELIMITER);
    }

    /**
     * A dialog field which allows the user to enter any HTML string they wish.
     *
     * @return The {@link Breadcrumb} HTML delimiter.
     */
    @DialogField(fieldLabel = "Delimiter HTML", ranking = 3)
    public String getHtmlDelimiter() {
        return get("htmlDelimiter", "");
    }

    public String getDelimiter() {
        if (!getHtmlDelimiter().isEmpty()) {
            return getHtmlDelimiter();
        } else {
            String iconDelimiterHtml = String.format(ICON_DELIMITER_HTML, getIconDelimiter());
            return iconDelimiterHtml;
        }
    }

    /**
     * A dialog field which allows the user to check a box if they wish to hide the current page in the breadcrumb.
     *
     * @return <code>true</code> will hide the last item in the {@link BreadcrumbItem} list. <code>false</code> will display the last item.
     */
    @DialogField(fieldLabel = "Hide current page in Breadcrumb?", ranking = 4)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
    public boolean getHideCurrentPageInBreadcrumb() {
        return get("hideCurrentPageInBreadcrumb", false);
    }

    /**
     * A dialog field which allows the user to choose the type of root page to use for this breadcrumb instance.
     *
     * @ return A string representing the option the user has chosen.
     */
    @DialogField(fieldLabel = "Root Page Type", fieldDescription = "The type of page that the breadcrumb will display as the root page.", ranking = 6)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE),
            @Option(text = "Home Page", value = HomePage.RDF_TYPE)
    })
    public String getRootPageType() {
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }

    /**
     * A dialog field which allows the user to specify what elements to display in most breadcrumb pages.
     *
     * @return A {@link BreadcrumbItemConfigNode}
     */
    @DialogField(ranking = 7)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/", title = "Current Page Config")
    public BreadcrumbItemConfigNode getCurrentBreadcrumbItemConfigNode() {
        Resource intermediaryPageResource = getResource().getChild(CURRENT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH);
        if (intermediaryPageResource != null) {
            return new BreadcrumbItemConfigNode(intermediaryPageResource.adaptTo(BasicNode.class));
        } else {
            return null;
        }
    }

    /**
     * A dialog field which allows the user to specify what elements to display in most breadcrumb pages.
     *
     * @return A {@link BreadcrumbItemConfigNode}
     */
    @DialogField(ranking = 8)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/", title = "Intermediary Page Config")
    public BreadcrumbItemConfigNode getIntermediaryBreadcrumbItemConfigNode() {
        Resource intermediaryPageResource = getResource().getChild(INTERMEDIARY_BREADCRUMB_PAGE_OPTIONS_NODE_PATH);
        if (intermediaryPageResource != null) {
            return new BreadcrumbItemConfigNode(intermediaryPageResource.adaptTo(BasicNode.class));
        } else {
            return null;
        }
    }

    /**
     * A dialog field which allows the user to specify what fields to display in the root page.
     *
     * @return A properly configured {@link BreadcrumbItemConfigNode} for a root page.
     */
    @DialogField(ranking = 9)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH + "/", title = "Root Page Config")
    public BreadcrumbItemConfigNode getRootBreadcrumbItemConfigNode() {
        Resource rootPageResource = getResource().getChild(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PATH);
        if (rootPageResource != null) {
            return new BreadcrumbItemConfigNode(rootPageResource.adaptTo(BasicNode.class));
        } else {
            return null;
        }
    }
}
