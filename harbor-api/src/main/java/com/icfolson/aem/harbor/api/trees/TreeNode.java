package com.icfolson.aem.harbor.api.trees;

import java.util.Iterator;

/**
 * Represents a single Node in a Tree.  The node will have children of type T, however the node itself
 * need not be of type T.  In general practice however, having a homogeneous tree is desired and most often
 * the node and its children will be of the same type.
 *
 * @param <T> The type of the child nodes under this node
 */
public interface TreeNode<T> {

    Iterable<T> getChildNodes();

    boolean isHasChildNodes();

    Iterator<T> getChildNodesIterator();

}
