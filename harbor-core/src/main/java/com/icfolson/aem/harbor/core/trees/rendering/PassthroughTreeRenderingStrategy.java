package com.icfolson.aem.harbor.core.trees.rendering;

import com.icfolson.aem.harbor.api.trees.TreeNode;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;

public class PassthroughTreeRenderingStrategy<T extends TreeNode> implements TreeRenderingStrategy<T, PassthroughTree<T>> {

    @Override
    public PassthroughTree<T> toRenderableTree(T rootNode) {
        return new PassthroughTree<>(rootNode);
    }
}