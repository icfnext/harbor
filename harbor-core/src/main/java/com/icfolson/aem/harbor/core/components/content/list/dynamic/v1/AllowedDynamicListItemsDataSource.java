package com.icfolson.aem.harbor.core.components.content.list.dynamic.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractAllowedDynamicTypesDataSource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {
    ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + AllowedDynamicListItemsDataSource.RESOURCE_TYPE
})
public class AllowedDynamicListItemsDataSource  extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewDynamicListItem.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return DynamicListItem.RESOURCE_TYPE;
    }

}
