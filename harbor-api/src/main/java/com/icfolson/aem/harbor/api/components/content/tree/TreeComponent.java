package com.icfolson.aem.harbor.api.components.content.tree;

import com.icfolson.aem.harbor.api.datastructure.tree.TreeNode;

public interface TreeComponent<T> {

    TreeNode<T> getTree();

}
