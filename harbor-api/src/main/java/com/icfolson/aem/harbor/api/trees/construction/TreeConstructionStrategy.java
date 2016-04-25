package com.icfolson.aem.harbor.api.trees.construction;

import com.icfolson.aem.harbor.api.trees.TreeNode;
import com.google.common.base.Optional;

/**
 * A Strategy used to construct a Tree of nodes
 *
 * @param <T>
 */
public interface TreeConstructionStrategy<T extends TreeNode> {

	/**
	 * Construction produces a Parent object representing the rooted tree. If
	 * construction does not result in a root then an absent Optional is
	 * returned representing the absence of a list root.
	 *
	 * @return An optional Parent representing the rooted list or
	 *         Optional.absent if no root can be produced by the Strategy.
	 */
	Optional<T> construct();

}
