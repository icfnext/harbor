package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractAllowedDynamicTypesDataSource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {
    ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + AllowedAccordionItemTypeDataSource.RESOURCE_TYPE
})
public class AllowedAccordionItemTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewAccordionItem.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return DynamicAccordionItem.RESOURCE_TYPE;
    }

}
