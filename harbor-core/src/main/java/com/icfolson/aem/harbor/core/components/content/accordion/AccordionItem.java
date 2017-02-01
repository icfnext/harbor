package com.icfolson.aem.harbor.core.components.content.accordion;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Iterables;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Accordion Item",
    name = "accordion/accordionitem",
    actions = { "text: Accordion Item", "-", "edit", "delete" },
    listeners = {
        @Listener(name = "afteredit", value = "REFRESH_SELF"),
        @Listener(name = "afterdelete", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    //noDecoration = true, //when enabled, it breakes the rollover edit layout, but it's needed for the collapse to work
    layout = "rollover",
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.Accordion.moveUp( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp") }),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.Accordion.moveDown( this )}",
            additionalProperties = { @ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown") })
    }
)
@Model(adaptables = Resource.class)
public class AccordionItem extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/accordion/accordionitem";

    private Boolean open;

    @DialogField(fieldLabel = "Title", fieldDescription = "The title of the accordion item.")
    @TextField
    public String getTitle() {
        return IconUtils.iconify(get("title", getName()));
    }

    public String getName() {
        return getResource().getName();
    }

    public String getAccordionUniqueId() {
        return getResource().getParent().adaptTo(Accordion.class).getId();
    }

    public Boolean isOpen() {
        if (open == null) {
            final Resource parent = getResource().getParent();
            final Accordion accordion = parent.adaptTo(Accordion.class);

            open = Iterables.get(parent.getChildren(), 0).getPath().equals(getPath()) && accordion.isOpenFirstItem();
        }

        return open;
    }
}