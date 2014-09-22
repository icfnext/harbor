package com.citytechinc.cq.harbor.proper.core.trees.rendering;

import com.citytechinc.cq.harbor.proper.api.trees.TreeNode;
import com.citytechinc.cq.harbor.proper.api.trees.rendering.TreeRenderingStrategy;

public class PassthroughTreeRenderingStrategy<T extends TreeNode> implements TreeRenderingStrategy<T, PassthroughTree<T>> {

    @Override
    public PassthroughTree<T> toRenderableTree(T rootNode) {
        return new PassthroughTree<T>(rootNode);
    }

}
