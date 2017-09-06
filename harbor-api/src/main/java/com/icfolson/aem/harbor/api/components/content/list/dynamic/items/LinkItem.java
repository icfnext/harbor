package com.icfolson.aem.harbor.api.components.content.list.dynamic.items;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;

public interface LinkItem extends DynamicListItem {

    String getUrl();

    String getLabel();

}
