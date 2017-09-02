package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordion;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        value = "Dynamic Accordion (v1)",
        actions = { "text: Dynamic Accordion (v1)", "edit", "-", "copymove", "delete", "-", "insert" },
        isContainer = true,
        group = ComponentGroups.HARBOR_SCAFFOLDING,
        name = "dynamicaccordion/v1/dynamicaccordion",
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Item",
                        handler = "function() { Harbor.Components.DynamicAccordion.v1.DynamicAccordion.addItem( this, '" + "/apps/" + NewAccordionItem.RESOURCE_TYPE + "/" + NewAccordionItem.DIALOG_FILE_NAME + "' ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        } )
        } )
@Model(adaptables = Resource.class, adapters = DynamicAccordion.class, resourceType = DefaultDynamicAccordion.RESOURCE_TYPE)
public class DefaultDynamicAccordion implements DynamicAccordion<DynamicAccordionItem> {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/v1/dynamicaccordion";

    @Inject
    private Resource resource;

    public List<DynamicAccordionItem> getItems() {
        return Lists.newArrayList(resource.getChildren())
                .stream()
                .map(r -> r.adaptTo(DynamicAccordionItem.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(resource.getPath());
    }

}
