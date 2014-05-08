package com.citytechinc.cq.harbor.proper.lists.rendering;

public interface ListRenderingStrategy <T, R extends Iterable<?>> {

    public R toRenderableList(Iterable<T> itemIterable);

}
