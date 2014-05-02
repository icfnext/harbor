package com.citytechinc.cq.harbor.components.content.list;

import com.google.common.base.Optional;

import java.util.Iterator;
import java.util.List;

public interface ListComponent <T extends Iterable> {

    /**
     * Produces an Iterable over the elements which constitute the List which a given component instance represents.
     *
     * @return An Iterable over the elements which constitute the List which a given component instance represents
     */
    public T getItems();

    /**
     * Produces an Iterator from the Iterable returned by {@link #getItems()}.  This exists largely for
     * use in JSP where the c:forEach loop does not accept an Iterable.
     *
     * @return Iterable based on the results of {@link #getItems()}
     */
    public Iterator<?> getIterator();

}
