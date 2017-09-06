package com.icfolson.aem.harbor.api.components.content.dynamicaccordion;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

import java.util.List;

public interface DynamicAccordion<T extends DynamicAccordionItem> extends Identifiable, Classifiable {

    List<T> getItems();

}
