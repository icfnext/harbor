package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.components.ComponentConstants;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.day.cq.wcm.api.Template;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

//TODO: Go through each field, decide if they should "getInherited"
@Component(value = "Breadcrumb", tabs = {
        @Tab(title = "Breadcrumb"),
        @Tab(title = "Current Page"),
        @Tab(title = "Intermediary Pages"),
        @Tab(title = "Root Page")
})
 public class Breadcrumb extends AbstractComponent {

    private static final String DEFAULT_COMBINATION_DISPLAY_TYPE_VALUE = "combo";
    private static final String COMBINATION_DISPLAY_TYPE_NAME = "Combination of both";
    private static final String ICON_ONLY_DISPLAY_TYPE_NAME = "Display icon only";
    private static final String TITLE_ONLY_DISPLAY_TYPE_NAME = "Display page title only";
    private static final String DEFAULT_ICON_ONLY_DISPLAY_TYPE_VALUE = "icon";
    private static final String DEFAULT_TITLE_ONLY_DISPLAY_TYPE_VALUE = "title";
    private static final String DEFAULT_DELIMITER = "fa-bootstrap-slash";
    private List<PageDecorator> trail;

    public Breadcrumb(ComponentRequest request) {
        super(request);
    }

    public List<PageDecorator> getTrail() {
        if (trail != null) {
            return trail;
        }

        trail = Lists.newArrayList();

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
            trail.add(currentTrailPage);

            while (currentTrailPage != null && !currentTrailPage.getPath().equals(rootPage.get().getPath())) {

                currentTrailPage = currentTrailPage.getParent();

                if (!currentTrailPage.isHideInNav()) {
                    trail.add(currentTrailPage);
                }

            }

        }

        trail = Lists.reverse(trail);

        return trail;
    }

    @DialogField(fieldLabel = "Hide current page in Breadcrumb?", ranking = 1)
    @Selection(type = Selection.CHECKBOX, options = @Option(value = "true"))
    public boolean getHideCurrentPageInBreadcrumb() {
        //TODO: getInherited?
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

    @DialogField(fieldLabel = "Current Page Display Type", fieldDescription = "The way the current page will be displayed, as either an icon, a title, or both.", ranking = 3)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = COMBINATION_DISPLAY_TYPE_NAME, value = DEFAULT_COMBINATION_DISPLAY_TYPE_VALUE),
            @Option(text = ICON_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_ICON_ONLY_DISPLAY_TYPE_VALUE),
            @Option(text = TITLE_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_TITLE_ONLY_DISPLAY_TYPE_VALUE)
    })
    public String getCurrentPageDisplayType() {
        return get("currentPageDisplayType", DEFAULT_COMBINATION_DISPLAY_TYPE_VALUE);
    }

    @DialogField(fieldLabel = "Current Page Delimiter Icon", fieldDescription = "The icon that will divide the current page from the rest of the pages.", ranking = 4)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getCurrentPageDelimiter() {
        return get("currentPageDelimiter", DEFAULT_DELIMITER);
    }

    @DialogField(fieldLabel = "Intermediary Page Display Type", fieldDescription = "The way intermediary pages will be displayed, as either an icon, a title, or both.", ranking = 5)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = COMBINATION_DISPLAY_TYPE_NAME, value = DEFAULT_COMBINATION_DISPLAY_TYPE_VALUE),
            @Option(text = ICON_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_ICON_ONLY_DISPLAY_TYPE_VALUE),
            @Option(text = TITLE_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_TITLE_ONLY_DISPLAY_TYPE_VALUE)
    })
    public String getIntermediaryPageDisplayType() {
        return get("intermediaryPageDisplayType", DEFAULT_COMBINATION_DISPLAY_TYPE_VALUE);
    }

    @DialogField(fieldLabel = "Intermediary Page Delimiter Icon", fieldDescription = "The icon that will divide the intermediary pages from the rest of the pages.", ranking = 6)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getIntermediaryPageDelimiter() {
        return get("intermediaryPageDelimiter", DEFAULT_DELIMITER);
    }

    @DialogField(fieldLabel = "Root Page Display Type", fieldDescription = "The way the current page will be displayed, as either an icon, a title, or both.", ranking = 7)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = ICON_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_ICON_ONLY_DISPLAY_TYPE_VALUE),
            @Option(text = TITLE_ONLY_DISPLAY_TYPE_NAME, value = DEFAULT_TITLE_ONLY_DISPLAY_TYPE_VALUE)
    })
    public String getRootPageDisplayType() {
        return get("rootPageDisplayType", DEFAULT_TITLE_ONLY_DISPLAY_TYPE_VALUE);
    }

    @DialogField(fieldLabel = "Root Page Delimiter Icon", fieldDescription = "The icon that will divide the root page from the rest of the pages.", ranking = 8)
    @Selection(type = Selection.SELECT, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
    public String getRootPageDelimiter() {
        return get("rootPageDelimiter", DEFAULT_DELIMITER);
    }
}
