package com.icfolson.aem.harbor.api.components.content.breadcrumb;

public interface BreadcrumbNavigation<T extends BreadcrumbNavigationItem> {

    /**
     * Gets the trail of BreadcrumbNavigationItems which constitute the navigation
     *
     * @return BreadcrumbNavigationItems trail
     */
    Iterable<T> getTrail();

    /**
     * When true, the current page will be included in the Breadcrumb trail.
     *
     * @return Whether to include the current page in the Breadcrumb trail
     */
    boolean includeCurrentPage();

    /**
     * When true, the root page will be included in the Breadcrumb trail.
     *
     * @return Whether to include the root page in the Breadcrumb trail
     */
    boolean includeRootPage();

    /**
     * The length of the Breadcrumb trail
     *
     * @return The length of the Breadcrumb trail
     */
    int getLength();

}
