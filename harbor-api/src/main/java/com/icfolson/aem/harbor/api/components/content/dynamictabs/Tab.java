package com.icfolson.aem.harbor.api.components.content.dynamictabs;

public interface Tab {

    String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tab";

    public String getLabel();

    public String getId();

    public String getType();

    public String getPath();

}
