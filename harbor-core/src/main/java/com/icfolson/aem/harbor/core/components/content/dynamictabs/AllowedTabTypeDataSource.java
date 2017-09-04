package com.icfolson.aem.harbor.core.components.content.dynamictabs;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.Tab;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = AllowedTabTypeDataSource.RESOURCE_TYPE)
public class AllowedTabTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewTab.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return Tab.RESOURCE_TYPE;
    }

}
