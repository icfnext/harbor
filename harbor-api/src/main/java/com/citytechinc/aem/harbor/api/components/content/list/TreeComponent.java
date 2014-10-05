package com.citytechinc.aem.harbor.api.components.content.list;

import com.citytechinc.aem.harbor.api.trees.Tree;

public interface TreeComponent<T extends Tree> {

    public T getTree();

    public boolean isHasRoot();

}
