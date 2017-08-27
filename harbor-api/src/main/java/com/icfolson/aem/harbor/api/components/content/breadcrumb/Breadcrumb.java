package com.icfolson.aem.harbor.api.components.content.breadcrumb;

public interface Breadcrumb<T extends BreadcrumbItem> {

    Iterable<T> getTrail();

    boolean includeCurrentPage();

    boolean includeRootPage();

}
