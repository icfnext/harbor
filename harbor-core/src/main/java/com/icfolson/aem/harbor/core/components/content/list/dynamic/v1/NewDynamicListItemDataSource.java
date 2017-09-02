package com.icfolson.aem.harbor.core.components.content.list.dynamic.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewDynamicListItemDataSource.RESOURCE_TYPE)
public class NewDynamicListItemDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewDynamicListItem.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return DynamicListItem.RESOURCE_TYPE;
    }

}
