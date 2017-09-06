package com.icfolson.aem.harbor.api.components.content.accordion;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

import java.util.List;

public interface Accordion extends Identifiable, Classifiable {

    List<AccordionItem> getItems();

    boolean isHasItems();

    boolean isOpenFirstItem();

}
