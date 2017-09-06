package com.icfolson.aem.harbor.api.components.content.tabs;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface Tab extends Identifiable, Classifiable {

    String getLabel();

    String getType();

    String getPath();

}
