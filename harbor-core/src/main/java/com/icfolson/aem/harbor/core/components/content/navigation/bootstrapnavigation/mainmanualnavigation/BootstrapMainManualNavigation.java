package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import com.icfolson.aem.library.api.node.BasicNode;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "Main Manual Navigation",
    group = ComponentGroups.HARBOR_NAVIGATION,
    actions = { "text:Main Manual Navigation", "-", "edit", "-", "delete" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Navigation Item",
            handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"),
    },
    allowedParents = "*/parsys",
    path = "content/navigation")
@Model(adaptables = Resource.class)
public class BootstrapMainManualNavigation extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainmanualnavigation";

    private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    @DialogField(fieldLabel = "Enable Sticky Navigation?",
        fieldDescription = "Disabled in Edit mode. Use Preview mode to view.", ranking = 1)
    @Switch(offText = "No", onText = "Yes")
    public Boolean isSticky() {
        return getInherited("sticky", false);
    }

    @DialogField(fieldLabel = "Brand Link Image", fieldDescription = "Brand Link Image", ranking = 2)
    @Html5SmartImage(tab = false, allowUpload = false, uploadUrl = "", title = "Drag & Drop Image")
    public Optional<String> getBrandLinkImage() {
        return getImageSourceInherited("brandLinkImage");
    }

    @DialogField(fieldLabel = "Show Brand Link",
        fieldDescription = "Enable this to display a link to the root path as the first navigation element",
        ranking = 3)
    @Switch(offText = "No", onText = "Yes")
    public Boolean isShowBrandLink() {
        return getInherited("showBrandLink", false);
    }

    @DialogField(fieldLabel = "Brand Link Text",
        fieldDescription = "Text to present as the brand of the navigation bar", ranking = 4)
    @TextField
    public String getBrandLinkText() {
        return getInherited("brandLinkText", "");
    }

    @DialogField(fieldLabel = "Brand Link", fieldDescription = "The page or URL the brand should link to", ranking = 5)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public Link getBrandLink() {
        return getAsLink("brandLink").or(getLink());
    }

    @DialogField(fieldLabel = "Full Width", ranking = 6)
    @Switch(offText = "No", onText = "Yes")
    public boolean isFullWidth() {
        return get("fullWidth", false);
    }

    public String getContainerClass() {
        return isFullWidth() ? Bootstrap.CONTAINER_FULL_WIDTH_CLASS : Bootstrap.CONTAINER_CLASS;
    }

    public String getBrandLinkImageSrc() {
        return getBrandLinkImage().or("");
    }

    public boolean isHasBrandLinkImage() {
        return getBrandLinkImage().isPresent();
    }

    public List<BootstrapMainNavigationElement> getBootstrapMainNavigationElementList() {
        if (bootstrapMainNavigationElementList == null) {
            bootstrapMainNavigationElementList = getComponentNodesInherited("elements")
                .stream()
                .map(element -> element.getResource().adaptTo(BootstrapMainNavigationElement.class))
                .collect(Collectors.toList());
        }

        return bootstrapMainNavigationElementList;
    }
}
