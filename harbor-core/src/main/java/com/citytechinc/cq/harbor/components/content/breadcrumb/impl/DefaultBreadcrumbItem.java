package com.citytechinc.cq.harbor.components.content.breadcrumb.impl;

import com.citytechinc.cq.harbor.components.content.breadcrumb.BreadcrumbItem;
import com.citytechinc.cq.harbor.components.content.breadcrumb.BreadcrumbItemConfigNode;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import org.apache.sling.api.adapter.Adaptable;

public class DefaultBreadcrumbItem implements BreadcrumbItem, Adaptable {
    private HierarchicalPage page;
    private BreadcrumbItemConfigNode breadcrumbTrailNode;

    public DefaultBreadcrumbItem(PageDecorator page) {
        this.page = page.adaptTo(HierarchicalPage.class);
    }

    public DefaultBreadcrumbItem(HierarchicalPage page) {
        this.page = page;
    }

    public BreadcrumbItemConfigNode getBreadcrumbTrailNode() {
        return breadcrumbTrailNode;
    }

    public void setBreadcrumbTrailNode(BreadcrumbItemConfigNode breadcrumbTrailNode) {
        this.breadcrumbTrailNode = breadcrumbTrailNode;
    }

    public boolean isHideIcon() {
        return breadcrumbTrailNode.getHideIcon();
    }

    public boolean isHideTitle() {
        return breadcrumbTrailNode.getHideTitle();
    }

    public String getIconDelimiter() {
        return breadcrumbTrailNode.getDelimiterIcon();
    }

    public String getHtmlDelimiter() {
        return breadcrumbTrailNode.getDelimiterHtml();
    }

    public boolean getUseHtmlDelimiter() {
        return breadcrumbTrailNode.getUseHtmlDelimiter();
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

    @Override
    public String getPageIcon() {
        return getPage().getPageIcon();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <AdapterType> AdapterType adaptTo(final Class<AdapterType> type) {
        final AdapterType result;

        if (PageDecorator.class.isAssignableFrom(type)) {
            result = page.adaptTo(type);
        } else {
            return null;
        }

        return result;
    }
}
