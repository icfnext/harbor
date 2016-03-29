package com.citytechinc.aem.harbor.core.components.content.accordion;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;

import javax.inject.Inject;

@Component(
    value = "Accordion Item",
    name = "accordion/accordionitem",
    actions = { "text: Accordion Item", "-", "edit", "delete" },
    listeners = {
        @Listener(name = "afteredit", value = "REFRESH_SELF"),
        @Listener(name = "afterdelete", value = "REFRESH_PARENT")
    },
    group = ".hidden",
    //noDecoration = true, //when enabled, it breakes the rollover edit layout, but it's needed for the collapse to work
    layout = "rollover",
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Move Up", handler = "function(){Harbor.Components.Accordion.moveUp( this )}", additionalProperties = {@ActionConfigProperty(name = "icon", value = "coral-Icon--accordionUp")}),
        @ActionConfig(text = "Move Down", handler = "function(){Harbor.Components.Accordion.moveDown( this )}", additionalProperties = {@ActionConfigProperty(name = "icon", value = "coral-Icon--accordionDown")})
    }
)
@AutoInstantiate
@Model(adaptables = Resource.class)
public class AccordionItem extends AbstractComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccordionItem.class);

    @Inject
    private PageDecorator currentPage;

    public static final String TYPE = "harbor/components/content/accordion/accordionitem";

    @DialogField(fieldLabel = "Title", fieldDescription = "The title of the accordion item.")
    @TextField
    private String title;

    private String uniqueId;
    private String accordionUniqueId;
    private String name;
    private Boolean open;

    public String getTitle() {
        if (title == null) {
            title = IconUtils.iconify(this.get("title", this.getName()));
        }
        return title;
    }

    public String getName() {
        if (name == null) {
            name = getResource().getName();
        }
        return name;
    }

    public String getUniqueId() {
        if (uniqueId == null) {
            uniqueId = ComponentUtils.getUniqueIdentifierForResourceInPage(currentPage, getResource());
        }
        return uniqueId;
    }

    public String getAccordionUniqueId() {
        if (accordionUniqueId == null) {
            accordionUniqueId = ComponentUtils.getUniqueIdentifierForResourceInPage(currentPage, getResource().getParent());
        }
        return accordionUniqueId;
    }

    public boolean isOpen() {
        if (open != null) {
            return open;
        }

        open = getResource().getParent().getChildren().iterator().next().getPath().equals(getResource().getPath()) &&
                getResource().getParent().adaptTo(Accordion.class).isOpenFirstItem();

        return open;
    }
}