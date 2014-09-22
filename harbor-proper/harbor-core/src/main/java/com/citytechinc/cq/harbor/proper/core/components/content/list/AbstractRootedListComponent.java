package com.citytechinc.cq.harbor.proper.core.components.content.list;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.harbor.proper.api.components.content.list.TreeComponent;
import com.citytechinc.cq.harbor.proper.api.trees.Tree;
import com.citytechinc.cq.harbor.proper.api.trees.TreeNode;
import com.citytechinc.cq.harbor.proper.api.trees.construction.TreeConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.trees.rendering.TreeRenderingStrategy;
import com.google.common.base.Optional;

public abstract class AbstractRootedListComponent<T extends TreeNode, R extends Tree> extends
	AbstractComponent implements TreeComponent<R> {

	private Optional<R> renderableTreeOptional;
	private Optional<T> rawItemsOptional;

	protected abstract TreeConstructionStrategy<T> getTreeConstructionStrategy();

	protected abstract TreeRenderingStrategy<T, R> getTreeRenderingStrategy();

	@Override
	public R getTree() {
		if (renderableTreeOptional == null) {

			Optional<T> requestedRawItemsOptional = getRawItemsOptional();

			if (requestedRawItemsOptional.isPresent()) {
				renderableTreeOptional = Optional.of(getTreeRenderingStrategy().toRenderableTree(
                        requestedRawItemsOptional.get()));
			} else {
				renderableTreeOptional = Optional.absent();
			}
		}

		return renderableTreeOptional.orNull();
	}

	@Override
	public boolean isHasRoot() {
		R tree = getTree();

        return tree != null && tree.isHasRoot();
	}

	protected Optional<T> getRawItemsOptional() {
		if (rawItemsOptional == null) {
			rawItemsOptional = getTreeConstructionStrategy().construct();
		}

		return rawItemsOptional;
	}

}
