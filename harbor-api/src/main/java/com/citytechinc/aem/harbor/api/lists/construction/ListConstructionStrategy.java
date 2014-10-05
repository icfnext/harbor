package com.citytechinc.aem.harbor.api.lists.construction;

public interface ListConstructionStrategy<T> {

	public Iterable<T> construct();

}
