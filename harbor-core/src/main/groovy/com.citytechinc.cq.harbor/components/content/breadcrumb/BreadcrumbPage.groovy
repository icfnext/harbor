package com.citytechinc.cq.harbor.components.content.breadcrumb

import com.citytechinc.cq.library.content.page.PageDecorator

class BreadcrumbPage implements PageDecorator {

    @Delegate
    private final PageDecorator pageDecorator

    private final Boolean root;
    private final Boolean current;

    protected BreadcrumbPage(PageDecorator pageDecorator, Boolean root, Boolean current) {
        this.pageDecorator = pageDecorator
        this.root = root
        this.current = current
    }

    public Boolean isRoot() {
        return root;
    }

    public Boolean isCurrent() {
        return current;
    }

    public static BreadcrumbPage forRootPage(PageDecorator pageDecorator) {
        return new BreadcrumbPage(pageDecorator, true, false)
    }

    public static BreadcrumbPage forCurrentPage(PageDecorator pageDecorator) {
        return new BreadcrumbPage(pageDecorator, false, true)
    }

    public static BreadcrumbPage forPage(PageDecorator pageDecorator) {
        return new BreadcrumbPage(pageDecorator, false, false)
    }

    public static BreadcrumbPage forRootCurrentPage(PageDecorator pageDecorator) {
        return new BreadcrumbPage(pageDecorator, true, true)
    }

}
