package com.icfolson.aem.harbor.core.components.content.dynamictabs;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.Tab;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewTabDataSource.RESOURCE_TYPE)
public class NewTabDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewTab.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return Tab.RESOURCE_TYPE;
    }

}
