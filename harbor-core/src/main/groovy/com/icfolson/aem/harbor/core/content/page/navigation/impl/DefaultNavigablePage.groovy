package com.icfolson.aem.harbor.core.content.page.navigation.impl

import com.google.common.base.Function
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage
import com.icfolson.aem.harbor.core.content.page.navigation.NavigablePages
import com.icfolson.aem.harbor.core.content.page.navigation.NavigationElementConfiguration
import com.icfolson.aem.library.api.link.NavigationLink
import com.icfolson.aem.library.api.page.PageDecorator

class DefaultNavigablePage implements NavigablePage {

    private static final Function<PageDecorator, String> PAGE_PATH = new Function<PageDecorator, String>() {
        @Override
        String apply(PageDecorator page) {
            page.path
        }
    }

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
        if (!navigablePageList) {
            navigablePageList = []

            if (navigationElementConfiguration.navigationDepth > 0) {
                pageDecorator.getChildren(navigationElementConfiguration.respectHideInNavigation).each { page ->
                    if (navigationElementConfiguration.currentPage.isPresent()) {
                        navigablePageList.add(NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(page,
                            navigationElementConfiguration.navigationDepth - 1,
                            navigationElementConfiguration.respectHideInNavigation,
                            navigationElementConfiguration.currentPage.get()))
                    } else {
                        navigablePageList.add(NavigablePages.forPageAndDepthAndChildPolicy(page,
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
        def currentPagePath = navigationElementConfiguration.currentPage.transform(PAGE_PATH).orNull()
        def isActive = currentPagePath && currentPagePath.startsWith(pageDecorator.path)

        pageDecorator.getNavigationLink(isActive)
    }
}
