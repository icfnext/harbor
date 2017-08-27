package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewTabDataSource.RESOURCE_TYPE)
public class NewTabDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewTab.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return DynamicTab.RESOURCE_TYPE;
    }

}
