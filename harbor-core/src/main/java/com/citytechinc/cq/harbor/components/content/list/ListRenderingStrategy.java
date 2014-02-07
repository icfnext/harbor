package com.citytechinc.cq.harbor.components.content.list;

public interface ListRenderingStrategy <T> {

    public String renderListItem(T item);

}
