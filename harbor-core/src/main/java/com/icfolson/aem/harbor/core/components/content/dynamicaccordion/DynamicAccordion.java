package com.icfolson.aem.harbor.core.components.content.dynamicaccordion;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.AccordionItem;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        value = "Dynamic Accordion",
        actions = { "text: Dynamic Accordion", "edit", "-", "copymove", "delete", "-", "insert" },
        isContainer = true,
        group = ComponentGroups.HARBOR,
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Item",
                        handler = "function() { Harbor.Components.DynamicAccordion.v1.DynamicAccordion.addItem( this, '" + "/apps/" + NewAccordionItem.RESOURCE_TYPE + "/" + NewAccordionItem.DIALOG_FILE_NAME + "' ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        } )
        } )
@Model(adaptables = Resource.class)
public class DynamicAccordion {

    @Inject
    private Resource resource;

    public List<AccordionItem> getItems() {
        return Lists.newArrayList(resource.getChildren()).stream().map(r -> r.adaptTo(AccordionItem.class)).collect(Collectors.toList());
    }

}
