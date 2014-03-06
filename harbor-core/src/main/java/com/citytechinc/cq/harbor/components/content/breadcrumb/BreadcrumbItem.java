package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.library.content.page.PageDecorator;

public interface BreadcrumbItem {

    public BreadcrumbItemConfigNode getBreadcrumbTrailNode();

    public void setBreadcrumbTrailNode(BreadcrumbItemConfigNode breadcrumbTrailNode);

    public boolean isHideIcon();

    public boolean isHideTitle();

    public String getIconDelimiter();

    public String getHtmlDelimiter();

    public boolean getDelimiterType();

    public String getHref();

    public String getTitle();

    public HierarchicalPage getPage();
}
