package com.citytechinc.cq.harbor.components.content.tree;

import java.util.List;

public interface TreeNodeRenderingStrategy <T> {

    public String renderNodeValue(TreeNode<T> treeNode);

    public String renderChildren(TreeNode<T> treeNode);

}
