package com.icfolson.aem.harbor.api.components.content.columnrow;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface Column extends Classifiable, Identifiable {

    String getPath();

    String getType();

    String getColumnWidthClasses();

}
