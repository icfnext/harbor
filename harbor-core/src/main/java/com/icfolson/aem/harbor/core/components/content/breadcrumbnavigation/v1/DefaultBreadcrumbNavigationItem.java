package com.icfolson.aem.harbor.core.components.content.breadcrumbnavigation.v1;

import com.icfolson.aem.harbor.api.components.content.breadcrumb.BreadcrumbNavigationItem;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.enums.TitleType;

import java.util.Optional;

public class DefaultBreadcrumbNavigationItem implements BreadcrumbNavigationItem {

    private final PageDecorator page;
    private final boolean rootPage;
    private final boolean currentPage;

    public DefaultBreadcrumbNavigationItem(final PageDecorator page) {
        this.page = page;
        this.rootPage = false;
        this.currentPage = false;
    }

    public DefaultBreadcrumbNavigationItem(final PageDecorator page, boolean rootPage, boolean currentPage) {
        this.page = page;
        this.rootPage = rootPage;
        this.currentPage = currentPage;
    }

    @Override
    public String getHref() {
        return page.getHref();
    }

    @Override
    public String getTitle() {
        return page.getTitle(TitleType.NAVIGATION_TITLE)
                .or(page.getTitle(TitleType.PAGE_TITLE)
                        .or(page.getTitle()));
    }

    @Override
    public PageDecorator getPage() {
        return page;
    }

    @Override
    public boolean isRootPage() {
        return rootPage;
    }

    public boolean isCurrentPage() {
        return currentPage;
    }

}