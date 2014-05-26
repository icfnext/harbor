package com.citytechinc.cq.harbor.proper.api.lists;

import java.util.Iterator;

public interface RootedItems<T> {

    public Iterable<T> getItems();

    public boolean getHasItems();

    public Iterator<T> getItemsIterator();

}
