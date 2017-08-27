package com.icfolson.aem.harbor.api.components.content.dynamictabs;

public interface DynamicTab {

    String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tab";

    public String getLabel();

    public String getId();

    public String getType();

    public String getPath();

}
