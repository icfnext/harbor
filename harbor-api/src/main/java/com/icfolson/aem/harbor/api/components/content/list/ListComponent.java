package com.icfolson.aem.harbor.api.components.content.list;

public interface ListComponent<T> {

    String UNORDERED_LIST_TYPE = "ul";
    String ORDERED_LIST_TYPE = "ol";
    String DEFINITION_LIST_TYPE = "dl";
    String CONTAINER_LIST_TYPE = "div";

    /**
     * Produces an Iterable over the elements which constitute the List which a
     * given component instance represents.
     *
     * @return An Iterable over the elements which constitute the List which a given component instance represents
     */
    Iterable<T> getItems();

    String getListType();

}
