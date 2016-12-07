package com.icfolson.aem.harbor.api.lists.construction;

public interface ListConstructionStrategy<T> {

    Iterable<T> construct();

}
