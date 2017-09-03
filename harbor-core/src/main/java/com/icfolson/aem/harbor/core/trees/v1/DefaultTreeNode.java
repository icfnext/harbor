package com.icfolson.aem.harbor.core.trees.v1;

import com.icfolson.aem.harbor.api.datastructure.tree.TreeNode;

import java.util.List;

public class DefaultTreeNode<T> implements TreeNode<T> {

    private final List<TreeNode<T>> childNodes;
    private final T value;
    private final int depth;

    public DefaultTreeNode(T value, List<TreeNode<T>> childNodes) {
        this(value, childNodes, 0);
    }

    public DefaultTreeNode(T value, List<TreeNode<T>> childNodes, int depth) {
        this.value = value;
        this.childNodes = childNodes;
        this.depth = depth;
    }

    @Override
    public Iterable<TreeNode<T>> getChildNodes() {
        return childNodes;
    }

    @Override
    public boolean isHasChildNodes() {
        return !childNodes.isEmpty();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public int getDepth() {
        return depth;
    }

}
