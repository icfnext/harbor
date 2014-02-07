package com.citytechinc.cq.harbor.components.content.navigation.manual;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Navigation Element",
        actions = {"text: Navigation Element", "-", "edit", "-", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.navigation")
        },
        listeners = {
                @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        },
        group = ".hidden"
)
public class NavigationElement extends AbstractComponent {
    public NavigationElement(ComponentRequest request) {
        super(request);
    }
}
