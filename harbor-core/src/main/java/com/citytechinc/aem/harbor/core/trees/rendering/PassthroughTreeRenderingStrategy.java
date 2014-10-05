package com.citytechinc.aem.harbor.core.trees.rendering;

import com.citytechinc.aem.harbor.api.trees.TreeNode;
import com.citytechinc.aem.harbor.api.trees.rendering.TreeRenderingStrategy;

public class PassthroughTreeRenderingStrategy<T extends TreeNode> implements TreeRenderingStrategy<T, PassthroughTree<T>> {

    @Override
    public PassthroughTree<T> toRenderableTree(T rootNode) {
        return new PassthroughTree<T>(rootNode);
    }

}
