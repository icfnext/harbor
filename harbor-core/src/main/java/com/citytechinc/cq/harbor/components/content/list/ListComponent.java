package com.citytechinc.cq.harbor.components.content.list;

import com.google.common.base.Optional;

import java.util.List;

public interface ListComponent <T> {

    public List<T> getListItems();

    public List<RenderableListItem<T>> getRenderableListItems();

}
