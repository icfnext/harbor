package com.icfolson.aem.harbor.core.components.content.list.dynamic;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = AllowedDynamicListItemsDataSource.RESOURCE_TYPE)
public class AllowedDynamicListItemsDataSource  extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewDynamicListItem.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return DynamicListItem.RESOURCE_TYPE;
    }

}
