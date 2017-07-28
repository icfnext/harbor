package com.icfolson.aem.harbor.core.components.content.dynamicaccordion;

import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.AccordionItem;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractAllowedDynamicTypesDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = AllowedAccordionItemTypeDataSource.RESOURCE_TYPE)
public class AllowedAccordionItemTypeDataSource extends AbstractAllowedDynamicTypesDataSource {

    public static final String RESOURCE_TYPE = NewAccordionItem.RESOURCE_TYPE + "/allowedoptions";

    @Override
    public String getItemResourceType() {
        return AccordionItem.RESOURCE_TYPE;
    }

}
