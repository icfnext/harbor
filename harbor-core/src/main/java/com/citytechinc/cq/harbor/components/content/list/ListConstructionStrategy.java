package com.citytechinc.cq.harbor.components.content.list;

import java.util.List;

public interface ListConstructionStrategy <T> {

    public List<T> constructList();

}
