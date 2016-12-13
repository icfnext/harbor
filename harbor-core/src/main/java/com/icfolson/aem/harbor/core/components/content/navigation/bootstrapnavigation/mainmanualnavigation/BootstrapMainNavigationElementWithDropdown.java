package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.Listener;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Navigation Element",
    actions = { "text: Navigation Element", "-", "edit", "-", "delete" },
    listeners = {
        @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
        @Listener(name = "afteredit", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    layout = "rollover",
    path = "content/navigation/bootstrapmainmanualnavigation",
    htmlTag = @HtmlTag(tagName = "li", cssClass = "dropdown"))
@Model(adaptables = Resource.class)
public class BootstrapMainNavigationElementWithDropdown extends BootstrapMainNavigationElement {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainmanualnavigation/bootstrapmainnavigationelementwithdropdown";

}
