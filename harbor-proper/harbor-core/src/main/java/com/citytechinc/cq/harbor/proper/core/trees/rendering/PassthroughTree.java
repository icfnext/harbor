package com.citytechinc.cq.harbor.proper.core.trees.rendering;

import com.citytechinc.cq.harbor.proper.api.trees.Tree;
import com.citytechinc.cq.harbor.proper.api.trees.TreeNode;

public class PassthroughTree<T extends TreeNode> implements Tree<T> {

    private final T root;

    public PassthroughTree(T root) {
        this.root = root;
    }

    @Override
    public T getRoot() {
        return root;
    }

    @Override
    public boolean isHasRoot() {
        return root != null;
    }

}
