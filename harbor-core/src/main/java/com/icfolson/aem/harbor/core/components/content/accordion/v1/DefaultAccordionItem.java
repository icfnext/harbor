package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Iterables;
import com.icfolson.aem.harbor.api.components.content.accordion.Accordion;
import com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Accordion Item (v1)",
    name = "accordion/v1/accordionitem",
    actions = { "text: Accordion Item (v1)", "-", "edit", "delete" },
    isContainer = true,
    listeners = {
        @Listener(name = "afteredit", value = "REFRESH_SELF"),
        @Listener(name = "afterdelete", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.Accordion.v1.Accordion.moveUp( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.Accordion.v1.Accordion.moveDown( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
    }
)
@Model(adaptables = Resource.class, adapters = AccordionItem.class, resourceType = DefaultAccordionItem.RESOURCE_TYPE)
public class DefaultAccordionItem extends AbstractComponent implements AccordionItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/accordion/v1/accordionitem";

    private Boolean open;
    private Integer index;
    private Accordion accordion;

    @DialogField(fieldLabel = "Title", fieldDescription = "The title of the accordion item.")
    @TextField
    public String getTitle() {
        return IconUtils.iconify(get("title", getDefaultTitle()));
    }

    @Override
    public Accordion getAccordion() {
        if (accordion == null) {
            accordion = getResource().getParent().adaptTo(Accordion.class);
        }

        return accordion;
    }

    @Override
    public Integer getItemIndex() {
        if (index == null) {
            index = getIndex();
        }

        return index;
    }

    protected String getDefaultTitle() {
        return "Accordion Item " + this.getItemIndex();
    }

}