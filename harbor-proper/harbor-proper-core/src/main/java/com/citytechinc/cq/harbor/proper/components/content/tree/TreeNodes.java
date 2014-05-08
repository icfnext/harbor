package com.citytechinc.cq.harbor.proper.components.content.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodes {

    private TreeNodes() {

    }

    public static <T> TreeNode<T> newBasicTreeNode(T value) {

        return new DefaultTreeNode<T>(value, new ArrayList<TreeNode<T>>());

    }

    public static <T> TreeNode<T> newBasicTreeNode(T value, List<TreeNode<T>> children) {

        return new DefaultTreeNode<T>(value, children);

    }

}
