package com.citytechinc.cq.harbor.proper.api.trees;

public interface Tree<T extends TreeNode> {

    public T getRoot();

    public boolean isHasRoot();

}
