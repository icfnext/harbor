package com.icfolson.aem.harbor.api.components.content.dynamictabs;

import com.icfolson.aem.harbor.api.components.mixins.identifiable.Identifiable;

public interface DynamicTab extends Identifiable {

    String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tab";

    public String getLabel();

    public String getType();

    public String getPath();

}
