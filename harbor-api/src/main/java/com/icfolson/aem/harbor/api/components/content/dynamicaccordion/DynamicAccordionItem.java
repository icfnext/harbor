package com.icfolson.aem.harbor.api.components.content.dynamicaccordion;

import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface DynamicAccordionItem extends Identifiable {

    String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/item";
    String DEFAULT_STYLE = "panel-default";

    String getHeadingText();

    String getType();

    String getPath();

    //TODO: Get Classification in interface

    default String getStyle() {
        return DEFAULT_STYLE;
    }

}
