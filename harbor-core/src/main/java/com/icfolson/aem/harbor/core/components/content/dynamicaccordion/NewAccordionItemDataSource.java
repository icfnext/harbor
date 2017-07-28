package com.icfolson.aem.harbor.core.components.content.dynamicaccordion;

import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.AccordionItem;
import com.icfolson.aem.harbor.core.components.dynamics.AbstractDynamicItemDataSource;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(resourceTypes = NewAccordionItemDataSource.RESOURCE_TYPE)
public class NewAccordionItemDataSource extends AbstractDynamicItemDataSource {

    public static final String RESOURCE_TYPE = NewAccordionItem.RESOURCE_TYPE + "/options";

    @Override
    public String getItemResourceType() {
        return AccordionItem.RESOURCE_TYPE;
    }

}
