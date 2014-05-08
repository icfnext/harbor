package com.citytechinc.cq.harbor.proper.components.content.tree;

public interface TreeNodeConstructionStrategy<T> {

    public TreeNode<T> construct();

}
