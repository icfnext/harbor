package com.icfolson.aem.harbor.core.components.content.page

import com.icfolson.aem.library.api.page.PageDecorator

class TrailPage implements PageDecorator {

    @Delegate
    private final PageDecorator pageDecorator

    private final Boolean root
    private final Boolean current

    protected TrailPage(PageDecorator pageDecorator, Boolean root, Boolean current) {
        this.pageDecorator = pageDecorator
        this.root = root
        this.current = current
    }

    Boolean isRoot() {
        return root
    }

    Boolean isCurrent() {
        return current
    }

    static TrailPage forRootPage(PageDecorator pageDecorator) {
        return new TrailPage(pageDecorator, true, false)
    }

    static TrailPage forCurrentPage(PageDecorator pageDecorator) {
        return new TrailPage(pageDecorator, false, true)
    }

    static TrailPage forPage(PageDecorator pageDecorator) {
        return new TrailPage(pageDecorator, false, false)
    }

    static TrailPage forRootCurrentPage(PageDecorator pageDecorator) {
        return new TrailPage(pageDecorator, true, true)
    }
}
