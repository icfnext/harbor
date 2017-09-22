package com.icfolson.aem.harbor.api.components.content.accordion;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface AccordionItem extends Identifiable, Classifiable {

    String getTitle();

    Accordion getAccordion();

    int getItemIndex();

    String getPath();

    String getType();

    boolean isExpanded();

}
