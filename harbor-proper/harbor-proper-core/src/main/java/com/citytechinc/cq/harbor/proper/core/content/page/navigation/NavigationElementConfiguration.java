package com.citytechinc.cq.harbor.proper.core.content.page.navigation;

import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

public class NavigationElementConfiguration {

    private final Boolean respectHideInNavigation;
    private final Integer navigationDepth;
    private final PageDecorator currentPage;

    public NavigationElementConfiguration(Boolean respectHideInNavigation, Integer navigationDepth) {
        this.respectHideInNavigation = respectHideInNavigation;
        this.navigationDepth = navigationDepth;
        this.currentPage = null;
    }

    public NavigationElementConfiguration(Boolean respectHideInNavigation, Integer navigationDepth, PageDecorator currentPage) {
        this.respectHideInNavigation = respectHideInNavigation;
        this.navigationDepth = navigationDepth;
        this.currentPage = currentPage;
    }


    public Boolean getRespectHideInNavigation() {
        return respectHideInNavigation;
    }

    public Integer getNavigationDepth() {
        return navigationDepth;
    }

    public Optional<PageDecorator> getCurrentPage() {
        return Optional.fromNullable(currentPage);
    }

}
