package com.icfolson.aem.harbor.api.components.content.breadcrumb;

public interface BreadcrumbNavigation<T extends BreadcrumbNavigationItem> {

    Iterable<T> getTrail();

    boolean includeCurrentPage();

    boolean includeRootPage();

    int getLength();

}
