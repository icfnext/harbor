package com.icfolson.aem.harbor.api.components.content.list.dynamic;

import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;

public interface DynamicList<T extends DynamicListItem> extends Classifiable, ListComponent<T> {
}
