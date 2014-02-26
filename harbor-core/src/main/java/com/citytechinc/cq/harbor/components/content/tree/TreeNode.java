package com.citytechinc.cq.harbor.components.content.tree;

import java.util.List;

public interface TreeNode <T> {

    public T getValue();

    public List<TreeNode<T>> getChildren();


}
