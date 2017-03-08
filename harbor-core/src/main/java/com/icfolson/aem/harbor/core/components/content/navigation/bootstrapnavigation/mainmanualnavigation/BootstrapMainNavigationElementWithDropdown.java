package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.google.common.collect.Lists;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import com.icfolson.aem.multicompositeaddon.widget.MultiCompositeField;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.List;

@Component(
    value = "Navigation Element with Dropdown",
    actions = { "text: Navigation Element with Dropdown", "-", "edit", "-", "delete" },
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

    @DialogField(fieldLabel = "Links", fieldDescription = "Links to display in this dropdown menu.", ranking = 4)
    @MultiCompositeField
    @Inject
    @Optional
    private List<BootstrapMainNavigationLink> links = Lists.newArrayList();

    @IgnoreDialogField
    @Override
    public Boolean isHasDropdown() {
        return false;
    }

    public List<BootstrapMainNavigationLink> getLinks() {
        return links;
    }
}
