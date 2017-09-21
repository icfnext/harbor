package com.icfolson.aem.harbor.core.content.page.v1

import com.icfolson.aem.harbor.api.content.page.HierarchicalPage
import com.icfolson.aem.library.api.page.PageDecorator

import javax.inject.Inject

abstract class AbstractHierarchicalPage implements HierarchicalPage, PageDecorator {

    @Inject @Delegate
    private PageDecorator page

}
