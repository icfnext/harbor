package com.citytechinc.cq.harbor.proper.core.content.page.navigation.construction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.construction.RootedListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.NavigablePages;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;

public class PageNavigationListConstructionStrategy extends AbstractComponent implements RootedListConstructionStrategy<NavigablePage> {

    private Optional<NavigablePage> navigationRoot;
    private Integer navigationDepth;
    private Boolean ignoreHideInNavigation;

    public PageNavigationListConstructionStrategy(ComponentRequest request) {
        super(request);

        this.navigationDepth = getInherited("navigationDepth", 1);
        this.ignoreHideInNavigation = getInherited("ignoreHideInNavigation", false);
    }

    @DialogField(fieldLabel = "Navigation Root", fieldDescription = "The page at which the Navigation will start")
    @PathField
    public Optional<NavigablePage> getNavigationRoot() {
        if (navigationRoot == null) {
            PageDecorator rootPage = getRootPage();

            if (rootPage != null) {
                navigationRoot = Optional.of(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(getRootPage(), getNavigationDepth(), !getIgnoreHideInNavigation(), currentPage));
            }
            else {
                navigationRoot = Optional.absent();
            }
        }

        return navigationRoot;
    }

    protected PageDecorator getRootPage() {
        Optional<String> rootPagePath = request.getComponentNode().get("navigationRoot", String.class);
        if (rootPagePath.isPresent()) {
            return request.getPageManager().getPage(rootPagePath.get());
        }

        return null;
    }

    @DialogField(fieldLabel = "Navigation Depth", fieldDescription = "The depth to which the navigation will search for child pages", additionalProperties = {@FieldProperty(name="emptyText", value="1")})
    @NumberField
    public Integer getNavigationDepth() {
        return navigationDepth;
    }

    public Boolean getIgnoreHideInNavigation() {
        return ignoreHideInNavigation;
    }

    @Override
    public Optional<NavigablePage> construct() {

        return getNavigationRoot();

    }
}
