package com.citytechinc.cq.harbor.proper.lists.construction;

import java.util.List;

public interface ListConstructionStrategy <T> {

    public List<T> construct();

}
