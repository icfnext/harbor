package com.citytechinc.cq.harbor.lists.rendering;

import java.util.List;

public interface ListRenderingStrategy <T, R extends Iterable<?>> {

    public R toRenderableList(Iterable<T> itemIterable);

}
