package com.icfolson.aem.harbor.api.components.content.list.linklist;

import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;

public interface LinkList<T extends ResourceBasedListableLink> extends Classifiable, ListComponent<T> {

}
