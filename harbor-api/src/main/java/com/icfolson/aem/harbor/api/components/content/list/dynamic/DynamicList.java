package com.icfolson.aem.harbor.api.components.content.list.dynamic;

import java.util.List;

public interface DynamicList {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist";

    public List<DynamicListItem> getItems();

}
