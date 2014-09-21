package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.harbor.proper.core.util.ComponentUtils;

@Component(
        value = "Navigation Element",
        actions = { "text: Navigation Element", "-", "edit", "-", "delete" },
        listeners = {@Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"), },
        group = ".hidden",
        layout = "rollover",
        path = "content/navigation/bootstrapmainmanualnavigation")
public class BootstrapMainNavigationElementWithDropdown extends BootstrapMainNavigationElement {

    public String getUniqueIdentifier() {
        return ComponentUtils.getUniqueIdentifierForResourceInPage(getCurrentPage(), getResource());
    }

}
