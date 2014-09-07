package com.citytechinc.cq.harbor.proper.api.components.content.list;

import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;

import java.util.Iterator;

public interface RootedListComponent <T extends RootedItems> {

    public T getRoot();

    public boolean getHasRoot();

}
