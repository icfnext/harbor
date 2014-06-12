package com.citytechinc.cq.harbor.proper.api.lists.construction;

public interface ListConstructionStrategy <T> {

    public Iterable<T> construct();

}
