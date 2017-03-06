package com.icfolson.aem.harbor.core.components.content.page

import com.icfolson.aem.library.api.page.PageDecorator
import groovy.transform.TupleConstructor

@TupleConstructor
class TrailPage implements PageDecorator {

    @Delegate
    PageDecorator pageDecorator

    Boolean root

    Boolean current

    Boolean isRoot() {
        root
    }

    Boolean isCurrent() {
        current
    }

    static TrailPage forRootPage(PageDecorator pageDecorator) {
        new TrailPage(pageDecorator, true, false)
    }

    static TrailPage forCurrentPage(PageDecorator pageDecorator) {
        new TrailPage(pageDecorator, false, true)
    }

    static TrailPage forPage(PageDecorator pageDecorator) {
        new TrailPage(pageDecorator, false, false)
    }

    static TrailPage forRootCurrentPage(PageDecorator pageDecorator) {
        new TrailPage(pageDecorator, true, true)
    }
}
