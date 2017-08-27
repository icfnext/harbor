package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.core.components.dynamics.v1.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewAccordionItemDataSource.RESOURCE_TYPE)
public class NewAccordionItemDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewAccordionItem.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return DynamicAccordionItem.RESOURCE_TYPE;
    }

}
