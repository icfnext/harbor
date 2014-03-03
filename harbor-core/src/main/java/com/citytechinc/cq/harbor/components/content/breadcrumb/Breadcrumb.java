package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.components.ComponentConstants;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.ListIterator;

//TODO: Go through each field, decide if they should "getInherited"
@Component(value = "Breadcrumb")
public class Breadcrumb extends AbstractComponent {

    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";
    private final static String ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX = "rootBreadcrumbPageOptions/";
    private final static String BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX = "breadcrumbPageOptions/";
    private final String HIDE_ICON_PROPERTY_NAME = "hideIcon";
    private final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";
    private List<BreadcrumbItem> trail;

    public Breadcrumb(ComponentRequest request) {
        super(request);
    }

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

    public List<BreadcrumbItem> formatListForBreadcrumb(List<PageDecorator> trailAsPageDecorators) {
        //The first, intermediate, and root pages all have special behavior. Instead of making the JSP worry about that, we're going to go ahead and do it here.
        //Here we use Lists.newArrayList in order to make the list mutable.
        trail = Lists.newArrayList(Lists.transform(trailAsPageDecorators, new Function<PageDecorator, BreadcrumbItem>() {
            public BreadcrumbItem apply(final PageDecorator pageDecorator) {
                return new BreadcrumbItem(pageDecorator);
            }
        }));

        ListIterator<BreadcrumbItem> trailListIterator = trail.listIterator();
        BreadcrumbItem currentBreadcrumbPage;
        while (trailListIterator.hasNext()) {
            currentBreadcrumbPage = trailListIterator.next();
            boolean isRoot = trail.indexOf(currentBreadcrumbPage) == 0;
            if (isRoot) {
                currentBreadcrumbPage.setBreadcrumbTrailNode(getRootBreadcrumbTrailConfigNode());
            } else {
                currentBreadcrumbPage.setBreadcrumbTrailNode(getBreadcrumbTrailConfigNode());
            }
            trailListIterator.set(currentBreadcrumbPage);
        }
        return trail;
    }

    @DialogField(fieldLabel = "Delimiter", ranking = 1)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getDelimiter() {
        return get("delimiter", DEFAULT_DELIMITER);
    }

    @DialogField(fieldLabel = "Hide current page in Breadcrumb?", ranking = 1)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
    public boolean getHideCurrentPageInBreadcrumb() {
        return get("hideCurrentPageInBreadcrumb", false);
    }

    @DialogField(fieldLabel = "Root Page Type", fieldDescription = "The type of page that the breadcrumb will display as the root page.", ranking = 2)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE),
            @Option(text = "Home Page", value = HomePage.RDF_TYPE)
    })
    public String getRootPageType() {
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }

    @DialogField(ranking = 3)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX, title = "Root Page Config")
    public BreadcrumbTrailConfigNode getRootBreadcrumbTrailConfigNode() {
        boolean hideIcon = get(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_ICON_PROPERTY_NAME, false);
        boolean hideTitle = get(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_TITLE_PROPERTY_NAME, false);
        return new BreadcrumbTrailConfigNode(hideIcon, hideTitle);
    }

    @DialogField(ranking = 4)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX, title = "Page Config")
    public BreadcrumbTrailConfigNode getBreadcrumbTrailConfigNode() {
        boolean hideIcon = get(BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_ICON_PROPERTY_NAME, false);
        boolean hideTitle = get(BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_TITLE_PROPERTY_NAME, false);
        return new BreadcrumbTrailConfigNode(hideIcon, hideTitle);
    }
}
