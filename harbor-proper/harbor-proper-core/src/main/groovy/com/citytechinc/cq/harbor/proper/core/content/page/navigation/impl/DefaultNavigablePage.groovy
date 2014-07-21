package com.citytechinc.cq.harbor.proper.core.content.page.navigation.impl

import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.NavigablePages
import com.citytechinc.cq.harbor.proper.core.content.page.navigation.NavigationElementConfiguration
import com.citytechinc.cq.library.content.link.NavigationLink
import com.citytechinc.cq.library.content.page.PageDecorator
import com.google.common.collect.Lists

class DefaultNavigablePage implements NavigablePage {

    @Delegate
    private final PageDecorator pageDecorator
    private final NavigationElementConfiguration navigationElementConfiguration

    private List<NavigablePage> navigablePageList

    public DefaultNavigablePage(PageDecorator pageDecorator, NavigationElementConfiguration navigationElementConfiguration) {
        this.pageDecorator = pageDecorator
        this.navigationElementConfiguration = navigationElementConfiguration
    }

    @Override
    public List<NavigablePage> getNavigableChildren() {

        if (navigablePageList == null) {
            navigablePageList = Lists.newArrayList()

            if (navigationElementConfiguration.navigationDepth > 0) {
                pageDecorator.getChildren(navigationElementConfiguration.respectHideInNavigation).each { PageDecorator currentPageDecorator ->

                    if (navigationElementConfiguration.currentPage.isPresent()) {
                        this.navigablePageList.add(
                                NavigablePages.forPageAndDepthAndChildPolicyAndCurrentPage(
                                        currentPageDecorator,
                                        navigationElementConfiguration.getNavigationDepth() - 1,
                                        navigationElementConfiguration.getRespectHideInNavigation(),
                                        navigationElementConfiguration.getCurrentPage().get()))
                    }
                    else {
                        this.navigablePageList.add(
                                NavigablePages.forPageAndDepthAndChildPolicy(
                                        currentPageDecorator,
                                        navigationElementConfiguration.getNavigationDepth() - 1,
                                        navigationElementConfiguration.getRespectHideInNavigation()))
                    }

                }
            }
        }

        return navigablePageList

    }

    @Override
    public boolean getHasItems() {
        return !getNavigableChildren().isEmpty();
    }

    @Override
    public Iterable<NavigablePage> getItems() {

        return getNavigableChildren()

    }

    @Override
    public Iterator<NavigablePage> getItemsIterator() {
        return getNavigableChildren().iterator();
    }

    @Override
    public NavigationLink getNavigationLink() {
        if (navigationElementConfiguration.getCurrentPage().isPresent()) {
            if (navigationElementConfiguration.currentPage.get().path.startsWith(pageDecorator.path)) {
                return pageDecorator.getNavigationLink(true)
            }
        }

        return pageDecorator.getNavigationLink()
    }

}
