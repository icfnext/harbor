package com.citytechinc.cq.harbor.proper.core.components.content.tree;

import java.util.List;

public class DefaultTreeNode <T> implements  TreeNode <T> {

    private final T value;
    private final List<TreeNode<T>> children;

    public DefaultTreeNode (T value, List<TreeNode<T>> children) {
        this.value = value;
        this.children = children;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public List<TreeNode<T>> getChildren() {
        return children;
    }

}
