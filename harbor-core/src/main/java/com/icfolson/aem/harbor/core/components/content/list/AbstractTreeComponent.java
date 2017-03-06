package com.icfolson.aem.harbor.core.components.content.list;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.components.content.list.TreeComponent;
import com.icfolson.aem.harbor.api.trees.Tree;
import com.icfolson.aem.harbor.api.trees.TreeNode;
import com.icfolson.aem.harbor.api.trees.construction.TreeConstructionStrategy;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.icfolson.aem.library.core.components.AbstractComponent;

public abstract class AbstractTreeComponent<T extends TreeNode, R extends Tree> extends
    AbstractComponent implements TreeComponent<R> {

    private Optional<R> renderableTreeOptional;

    private Optional<T> rawItemsOptional;

    protected abstract TreeConstructionStrategy<T> getTreeConstructionStrategy();

    protected abstract TreeRenderingStrategy<T, R> getTreeRenderingStrategy();

    @Override
    public R getTree() {
        if (renderableTreeOptional == null) {
            renderableTreeOptional = getRawItemsOptional().transform(items -> getTreeRenderingStrategy()
                .toRenderableTree(items));
        }

        return renderableTreeOptional.orNull();
    }

    @Override
    public boolean isHasRoot() {
        final R tree = getTree();

        return tree != null && tree.isHasRoot();
    }

    protected Optional<T> getRawItemsOptional() {
        if (rawItemsOptional == null) {
            rawItemsOptional = getTreeConstructionStrategy().construct();
        }

        return rawItemsOptional;
    }
}
