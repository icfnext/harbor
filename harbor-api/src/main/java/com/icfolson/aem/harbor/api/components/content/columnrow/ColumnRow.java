package com.icfolson.aem.harbor.api.components.content.columnrow;

import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classifiable;
import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

import java.util.List;

public interface ColumnRow<T extends Column> extends Classifiable, Identifiable {

    List<T> getColumns();

}
