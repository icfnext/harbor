package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.library.content.page.PageDecorator;

public class BreadcrumbItem {
    private PageDecorator page;
    private BreadcrumbTrailConfigNode breadcrumbTrailNode;

    public BreadcrumbItem(PageDecorator page) {
        this.page = page;
    }

    public BreadcrumbTrailConfigNode getBreadcrumbTrailNode() {
        return breadcrumbTrailNode;
    }

    public void setBreadcrumbTrailNode(BreadcrumbTrailConfigNode breadcrumbTrailNode) {
        this.breadcrumbTrailNode = breadcrumbTrailNode;
    }

    public boolean isHideIcon() {
        return breadcrumbTrailNode.getHideIcon();
    }

    public boolean isHideTitle() {
        return breadcrumbTrailNode.getHideTitle();
    }

    public String getHref() {
        return page.getHref();
    }

    public String getTitle() {
        return page.getTitle();
    }

    public PageDecorator getPage() {
        return page;
    }
}
