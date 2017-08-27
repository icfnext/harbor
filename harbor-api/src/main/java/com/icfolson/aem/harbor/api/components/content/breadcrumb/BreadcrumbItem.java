package com.icfolson.aem.harbor.api.components.content.breadcrumb;

import com.icfolson.aem.library.api.page.PageDecorator;

public interface BreadcrumbItem {

    String getHref();

    String getTitle();

    PageDecorator getPage();

    boolean isRootPage();

    boolean isCurrentPage();

}
