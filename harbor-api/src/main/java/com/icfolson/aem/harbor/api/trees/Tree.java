package com.icfolson.aem.harbor.api.trees;

import java.util.Optional;

public interface Tree<T extends TreeNode> {

    T getRoot();

    boolean isHasRoot();

    Optional<Integer> getDepth();

}
