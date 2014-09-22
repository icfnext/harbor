package com.citytechinc.cq.harbor.proper.api.components.content.list;

import com.citytechinc.cq.harbor.proper.api.trees.Tree;

public interface TreeComponent<T extends Tree> {

    public T getTree();

    public boolean isHasRoot();

}
