package com.citytechinc.cq.harbor.proper.core.components.content.tree;

public interface TreeNodeConstructionStrategy<T> {

    public TreeNode<T> construct();

}
