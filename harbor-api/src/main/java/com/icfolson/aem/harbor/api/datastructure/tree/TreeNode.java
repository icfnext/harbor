package com.icfolson.aem.harbor.api.datastructure.tree;

/**
 * Represents a single Node in a Tree.  The node will have children of type T, however the node itself
 * need not be of type T.  In general practice however, having a homogeneous tree is desired and most often
 * the node and its children will be of the same type.
 *
 * @param <T> Indicates the contents of the node
 */
public interface TreeNode<T> {

    Iterable<TreeNode<T>> getChildNodes();

    boolean isHasChildNodes();

    T getValue();

    int getDepth();

}
