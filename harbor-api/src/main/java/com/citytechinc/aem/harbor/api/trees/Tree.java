package com.citytechinc.aem.harbor.api.trees;

public interface Tree<T extends TreeNode> {

    public T getRoot();

    public boolean isHasRoot();

}
