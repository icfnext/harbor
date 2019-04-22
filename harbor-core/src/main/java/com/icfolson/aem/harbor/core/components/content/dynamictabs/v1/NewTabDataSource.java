package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractDynamicItemDataSource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {
    ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + NewTabDataSource.RESOURCE_TYPE
})
public class NewTabDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewTab.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return DynamicTab.RESOURCE_TYPE;
    }

}
