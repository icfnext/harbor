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
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.ListIterator;

@Component(value = "Breadcrumb",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "harbor.fontawesome"),
                @ContentProperty(name = "dependencies", value = "harbor.bootstrap")
        })
public class Breadcrumb extends AbstractComponent {

    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";
    private final static String ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX = "rootBreadcrumbPageOptions/";
    private final static String BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX = "breadcrumbPageOptions/";
    private final String HIDE_ICON_PROPERTY_NAME = "hideIcon";
    private final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";
    private final boolean ICON_DELIMITER_BOOLEAN = true;
    private final String ROOT_PAGE_SAME_AS_REST_OF_BREADCRUMB_DELIMITER_ICON_TYPE = "breadcrumbdelimiter";
    private final String ROOT_PAGE_PAGE_ICON_DELIMITER_TYPE = "pageicon";
    private List<BreadcrumbItem> trail;

    public Breadcrumb(final ComponentRequest request) {
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
                return pageDecorator.adaptTo(BreadcrumbItem.class);
            }
        }));

        ListIterator<BreadcrumbItem> trailListIterator = trail.listIterator();
        BreadcrumbItem currentBreadcrumbPage;
        //We know root is going to be the first object we iterate over. So instead of doing a check, we just set a flag.
        boolean isRoot = true;
        while (trailListIterator.hasNext()) {
            currentBreadcrumbPage = trailListIterator.next();
            if (isRoot) {
                String rootPageIcon = currentBreadcrumbPage.getPage().getPageIcon();
                currentBreadcrumbPage.setBreadcrumbTrailNode(getRootBreadcrumbItemConfigNode(rootPageIcon));
            } else {
                currentBreadcrumbPage.setBreadcrumbTrailNode(getBreadcrumbItemConfigNode());
            }
            trailListIterator.set(currentBreadcrumbPage);
            isRoot = false;
        }

        // If we're to hide the current page in the breadcrumb, we simply remove it from the list.
        if (getHideCurrentPageInBreadcrumb()) {
            trail.remove(trail.size() - 1);
        }
        return trail;
    }

    @DialogField(fieldLabel = "Use HTML delimiter?", ranking = 1)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "false"))
    public boolean getUseHTMLDelimiter() {
        return get("useHTMLDelimiter", true);
    }

    @DialogField(fieldLabel = "Delimiter Icon", ranking = 2)
    @Selection(type = Selection.COMBOBOX, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getIconDelimiter() {
        return get("iconDelimiter", DEFAULT_DELIMITER);
    }

    @DialogField(fieldLabel = "Delimiter HTML", ranking = 3)
    public String getHtmlDelimiter() {
        return get("htmlDelimiter", "");
    }

    @DialogField(fieldLabel = "Hide current page in Breadcrumb?", ranking = 4)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
    public boolean getHideCurrentPageInBreadcrumb() {
        return get("hideCurrentPageInBreadcrumb", false);
    }

    @DialogField(fieldLabel = "Root Page Type", fieldDescription = "The type of page that the breadcrumb will display as the root page.", ranking = 6)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE),
            @Option(text = "Home Page", value = HomePage.RDF_TYPE)
    })
    public String getRootPageType() {
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }

    @DialogField(ranking = 7)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX, title = "Root Page Config")
    public BreadcrumbItemConfigNode getRootBreadcrumbItemConfigNode(String rootPageIcon) {
        boolean hideIcon = get(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_ICON_PROPERTY_NAME, false);
        boolean hideTitle = get(ROOT_BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_TITLE_PROPERTY_NAME, false);
        boolean delimiterType;
        String icon;
        String rootPageIconType = getRootPageIconType();
        if (rootPageIconType.equals(ROOT_PAGE_PAGE_ICON_DELIMITER_TYPE)) {
            delimiterType = ICON_DELIMITER_BOOLEAN;
            icon = rootPageIcon;
        } else {
            delimiterType = getUseHTMLDelimiter();
            icon = getIconDelimiter();
        }
        return new BreadcrumbItemConfigNode(hideIcon, hideTitle, delimiterType, icon, getHtmlDelimiter());
    }

    @DialogField(ranking = 8)
    @DialogFieldSet(collapsible = true, collapsed = true, namePrefix = BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX, title = "Page Config")
    public BreadcrumbItemConfigNode getBreadcrumbItemConfigNode() {
        boolean hideIcon = get(BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_ICON_PROPERTY_NAME, false);
        boolean hideTitle = get(BREADCRUMB_PAGE_OPTIONS_NODE_PREFIX + HIDE_TITLE_PROPERTY_NAME, false);

        return new BreadcrumbItemConfigNode(hideIcon, hideTitle, getUseHTMLDelimiter(), getIconDelimiter(), getHtmlDelimiter());
    }

    @DialogField(fieldLabel = "Root Page Icon Type", fieldDescription = "The type of icon you wish to be displayed, for the home page.", ranking = 4)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Default breadcrumb icon", value = ROOT_PAGE_SAME_AS_REST_OF_BREADCRUMB_DELIMITER_ICON_TYPE),
            @Option(text = "Page Icon", value = ROOT_PAGE_PAGE_ICON_DELIMITER_TYPE)
    })
    public String getRootPageIconType() {
        return get("rootPageIconType", ROOT_PAGE_SAME_AS_REST_OF_BREADCRUMB_DELIMITER_ICON_TYPE);
    }
}
