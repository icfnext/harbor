package com.icfolson.aem.harbor.api.components.content.accordion;

import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface AccordionItem extends Identifiable {

    String getTitle();

    Accordion getAccordion();

    Integer getItemIndex();

}
