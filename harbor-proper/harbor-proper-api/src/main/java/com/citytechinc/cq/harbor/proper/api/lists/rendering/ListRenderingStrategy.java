package com.citytechinc.cq.harbor.proper.api.lists.rendering;

public interface ListRenderingStrategy <T, R extends Iterable<?>> {

    public R toRenderableList(Iterable<T> itemIterable);

}
