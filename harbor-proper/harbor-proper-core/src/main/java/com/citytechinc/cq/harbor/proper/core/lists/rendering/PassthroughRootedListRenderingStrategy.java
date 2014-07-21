package com.citytechinc.cq.harbor.proper.core.lists.rendering;

import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.RootedListRenderingStrategy;

public class PassthroughRootedListRenderingStrategy<T extends RootedItems> implements RootedListRenderingStrategy<T, T> {

    @Override
    public T toRenderableList(T rootedItem) {
        return rootedItem;
    }

}
