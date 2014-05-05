package com.citytechinc.cq.harbor.lists.construction;

import java.util.List;

public interface ListConstructionStrategy <T> {

    public List<T> construct();

}
