package com.citytechinc.cq.harbor.components.content.tree;

import com.citytechinc.cq.harbor.components.content.list.ListComponent;

public interface TreeNodeConstructionStrategy<T> {

    public TreeNode<T> construct();

}
