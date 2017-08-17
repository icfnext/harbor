package com.icfolson.aem.harbor.api.model.page.pagetree;

import com.icfolson.aem.harbor.api.trees.TreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;

public interface PageTreeNode<T extends PageTreeNode> extends TreeNode<T, PageDecorator> {

    boolean isRedirect();

    boolean isAlongActivePath();

    String getTitle();

    String getHref();

}
