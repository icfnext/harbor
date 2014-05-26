package com.citytechinc.cq.harbor.proper.api.content.page.navigation;

import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;
import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

/**
 * Represents a page in a Navigation Structure
 */
public interface NavigablePage extends PageDecorator, RootedItems<NavigablePage> {

    /**
     * Provides the list of the pages which can be navigated to directly from this page
     *
     * @return The list of pages which can be navigated to directly from this page
     */
    public List<NavigablePage> getNavigableChildren();

}
