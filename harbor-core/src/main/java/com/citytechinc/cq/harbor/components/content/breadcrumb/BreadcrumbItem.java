package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.google.common.base.Optional;

public class BreadcrumbItem {

    private HierarchicalPage page;
    private Optional<BreadcrumbItemConfigNode> breadcrumbItemConfigNode;

    public BreadcrumbItem(HierarchicalPage page, Optional<BreadcrumbItemConfigNode> breadcrumbItemConfigNode) {
        this.page = page;
        this.breadcrumbItemConfigNode = breadcrumbItemConfigNode;
    }

    public Optional<BreadcrumbItemConfigNode> getBreadcrumbItemConfigNode() {
        return breadcrumbItemConfigNode;
    }

    public boolean isHideIcon() {
        if (breadcrumbItemConfigNode.isPresent()) {
            return breadcrumbItemConfigNode.get().getHideIcon();
        } else {
            return false;
        }
    }

    public boolean isHideTitle() {
        if (breadcrumbItemConfigNode.isPresent()) {
            return breadcrumbItemConfigNode.get().getHideTitle();
        } else {
            return false;
        }
    }

    public String getHref() {
        return page.getHref();
    }

    public String getTitle() {
        return page.getTitle();
    }

    private HierarchicalPage getPage() {
        return page;
    }

    public String getPageIcon() {
        return getPage().getPageIcon();
    }
}
