package com.icfolson.aem.harbor.api.components.content.dynamicaccordion;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface DynamicAccordionItem extends Identifiable, Classifiable {

    String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/item";
    String DEFAULT_STYLE = "panel-default";

    String getHeadingText();

    String getType();

    String getPath();

    String getName();

    default String getStyle() {
        return DEFAULT_STYLE;
    }

}
