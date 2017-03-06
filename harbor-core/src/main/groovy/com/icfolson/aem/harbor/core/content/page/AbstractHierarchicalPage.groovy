package com.icfolson.aem.harbor.core.content.page

import com.icfolson.aem.harbor.api.content.page.HierarchicalPage
import com.icfolson.aem.library.api.page.PageDecorator

abstract class AbstractHierarchicalPage implements HierarchicalPage, PageDecorator {

    @Delegate
    private final PageDecorator page

    AbstractHierarchicalPage(PageDecorator page) {
        this.page = page
    }
}
