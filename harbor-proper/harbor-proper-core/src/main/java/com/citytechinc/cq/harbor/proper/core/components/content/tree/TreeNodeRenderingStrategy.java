package com.citytechinc.cq.harbor.proper.core.components.content.tree;

public interface TreeNodeRenderingStrategy <T> {

    public String renderNodeValue(TreeNode<T> treeNode);

    public String renderChildren(TreeNode<T> treeNode);

}
