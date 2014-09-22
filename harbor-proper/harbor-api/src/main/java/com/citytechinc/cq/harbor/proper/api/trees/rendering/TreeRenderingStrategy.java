package com.citytechinc.cq.harbor.proper.api.trees.rendering;

import com.citytechinc.cq.harbor.proper.api.trees.Tree;
import com.citytechinc.cq.harbor.proper.api.trees.TreeNode;

public interface TreeRenderingStrategy<T extends TreeNode, R extends Tree> {

	/**
	 * Transforms a Rooted Item represented by an implementation of the Parent
	 * interface into a renderable implementation of the Parent interface.
	 *
	 * @param rootNode Implementation of the Parent interface representing the
	 *            items to be rendered
	 * @return Renderable implementation of the Parent interface
	 */
	public R toRenderableTree(T rootNode);

}
