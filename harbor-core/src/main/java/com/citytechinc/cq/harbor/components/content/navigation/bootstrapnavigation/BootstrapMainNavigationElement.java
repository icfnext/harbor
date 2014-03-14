package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Navigation Element",
        actions = {"text: Navigation Element", "-", "edit", "-", "delete"},
        listeners = {
                @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        },
        group = ".hidden"
)
public class BootstrapMainNavigationElement extends AbstractComponent {
    public BootstrapMainNavigationElement(ComponentRequest req) {
        super(req);
    }
    public BootstrapMainNavigationElement(ComponentNode node) {
        super(node);
    }

    @DialogField(fieldLabel = "Element Title")
    public String getElementTitle(){
        return get("elementTitle", this.getResource().getName());
    }

    @DialogField(fieldLabel = "Element Link Target")
    @PathField
    public String getElementLinkTarget(){
        return get("elementLinkTarget", "#");
    }

    @DialogField(fieldLabel = "Has Dropdown?",
            fieldDescription = "This navigation element will be a dropdown/flyout element")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getHasDropdown(){
        return get("hasDropdown", "").equals("true");
    }

    public String getName(){
        return this.getResource().getName();
    }
}
