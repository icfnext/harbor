package com.icfolson.aem.harbor.core.content.page.navigation.impl

import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage
import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages
import com.icfolson.aem.harbor.core.content.page.navigation.NavigationElementConfiguration
import com.icfolson.aem.library.api.link.NavigationLink
import com.icfolson.aem.library.api.page.PageDecorator

class DefaultNavigablePage implements NavigablePage {

    @Delegate
    private final PageDecorator pageDecorator

    private final NavigationElementConfiguration navigationElementConfiguration

    private List<NavigablePage> navigablePageList

    DefaultNavigablePage(PageDecorator pageDecorator, NavigationElementConfiguration navigationElementConfiguration) {
        this.pageDecorator = pageDecorator
        this.navigationElementConfiguration = navigationElementConfiguration
    }

    @Override
    List<NavigablePage> getNavigableChildren() {
        if (navigablePageList == null) {
            navigablePageList = []

            if (navigationElementConfiguration.navigationDepth > 0) {
                pageDecorator.getChildren(navigationElementConfiguration.respectHideInNavigation).each {
                    PageDecorator currentPageDecorator ->
                        if (navigationElementConfiguration.currentPage.isPresent()) {
                            this.navigablePageList.add(
                                NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
                                    currentPageDecorator,
                                    navigationElementConfiguration.navigationDepth - 1,
                                    navigationElementConfiguration.respectHideInNavigation,
                                    navigationElementConfiguration.currentPage.get()))
                        } else {
                            this.navigablePageList.add(
                                NavigablePages.forPageAndDepthAndChildPolicy(
                                    currentPageDecorator,
                                    navigationElementConfiguration.navigationDepth - 1,
                                    navigationElementConfiguration.respectHideInNavigation))
                        }
                }
            }
        }

        navigablePageList
    }

    @Override
    boolean isHasChildNodes() {
        navigableChildren as Boolean
    }

    @Override
    Iterable<NavigablePage> getChildNodes() {
        navigableChildren
    }

    @Override
    Iterator<NavigablePage> getChildNodesIterator() {
        navigableChildren.iterator()
    }

    @Override
    NavigationLink getNavigationLink() {
        if (navigationElementConfiguration.currentPage.present) {
            if (navigationElementConfiguration.currentPage.get().path.startsWith(pageDecorator.path)) {
                return pageDecorator.getNavigationLink(true)
            }
        }

        pageDecorator.navigationLink
    }
}
