package com.icfolson.aem.harbor.api.content.page.navigation;

import com.icfolson.aem.harbor.api.trees.TreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;

import java.util.List;

/**
 * Represents a page in a Navigation Structure
 */
public interface NavigablePage extends PageDecorator, TreeNode<NavigablePage> {

    /**
     * Provides the list of the pages which can be navigated to directly from
     * this page
     *
     * @return The list of pages which can be navigated to directly from this page
     */
    List<NavigablePage> getNavigableChildren();
}
