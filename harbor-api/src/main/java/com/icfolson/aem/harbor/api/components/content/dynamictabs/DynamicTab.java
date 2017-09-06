package com.icfolson.aem.harbor.api.components.content.dynamictabs;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface DynamicTab extends Identifiable, Classifiable {

    String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tab";

    String getLabel();

    String getType();

    String getPath();

}
