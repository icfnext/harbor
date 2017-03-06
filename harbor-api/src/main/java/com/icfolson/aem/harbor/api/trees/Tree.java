package com.icfolson.aem.harbor.api.trees;

public interface Tree<T extends TreeNode> {

    T getRoot();

    boolean isHasRoot();

}
