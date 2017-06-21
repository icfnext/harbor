package com.icfolson.aem.harbor.api.components.content.dynamictabs;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

//@Model(adaptables = Resource.class)
public interface Tab {

    String RESOURCE_TYPE = "harbor/components/content/dynamictabs/tab";

    public String getLabel();

    public String getId();

    public String getType();

    public String getPath();

}
