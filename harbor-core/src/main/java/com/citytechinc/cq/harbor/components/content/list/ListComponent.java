package com.citytechinc.cq.harbor.components.content.list;

import com.google.common.base.Optional;

import java.util.Iterator;
import java.util.List;

public interface ListComponent <T extends Iterable> {

    public T getItems();

    public Iterator<?> getIterator();

}
