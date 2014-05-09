package com.citytechinc.cq.harbor.proper.api.lists.construction;

import java.util.List;

public interface ListConstructionStrategy <T> {

    public List<T> construct();

}
