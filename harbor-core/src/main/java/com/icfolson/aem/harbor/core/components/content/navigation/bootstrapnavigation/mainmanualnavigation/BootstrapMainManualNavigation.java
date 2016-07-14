package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import java.util.List;

import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.api.node.BasicNode;
import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        value = "Main Manual Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        actions = {
	        "text:Main Manual Navigation", "-", "edit", "-", "delete" },
        actionConfigs = {
	        @ActionConfig(xtype = "tbseparator"),
	        @ActionConfig(text = "Add Navigation Item", handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"),
        },
        allowedParents = "*/parsys",
        path = "content/navigation")
@AutoInstantiate(instanceName = "bootstrapMainManualNavigation")
@Model(adaptables = Resource.class)
public class BootstrapMainManualNavigation extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainmanualnavigation";

    private static final Logger LOG = LoggerFactory.getLogger(BootstrapMainManualNavigation.class);

	private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    @DialogField(fieldLabel = "Enable Sticky Navigation?", fieldDescription = "Disabled in Edit mode. Use Preview mode to view.")
    @Switch(offText = "No", onText = "Yes")
    private Boolean isSticky;

    @DialogField(fieldLabel = "Show Brand Link", fieldDescription = "Enable this to display a link to the root path as the first navigation element")
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Show", value = "show"),
            @Option(text = "Hide", value = "hide") })
    private Boolean showBrandLink;

    @DialogField(fieldLabel = "Brand Link Text", fieldDescription = "Text to present as the brand of the navigation bar")
    private String brandLinkText;

    @DialogField(fieldLabel = "Brand Link", fieldDescription = "The page or URL the brand should link to")
    @PathField
    private Link brandLink;

    @DialogField(fieldLabel = "Brand Link Image")
    @Html5SmartFile(ddGroups = "media", name="./brandLinkImage", fileNameParameter = "./brandLinkImage/fileName", fileReferenceParameter = "./brandLinkImage/fileReference")
    private Optional<String> brandLinkImage;

	public Boolean getIsSticky() {
        if (isSticky == null) {
            isSticky = getInherited("isSticky", false);
        }

        return isSticky;
	}

    public Boolean getShowBrandLink() {
        if (showBrandLink == null) {
            showBrandLink = getInherited("showBrandLink", "hide").equals("show");
        }

        return showBrandLink;
    }

    public String getBrandLinkText() {

        if (brandLinkText == null) {
            brandLinkText = getInherited("brandLinkText", StringUtils.EMPTY);
        }

        return brandLinkText;

    }

    public Link getBrandLink() {
        if (brandLink == null) {
            brandLink = getAsLink("brandLink").or(getLink());
        }

        return brandLink;
    }

    public Optional<String> getBrandLinkImage() {

        if (brandLinkImage == null) {
            brandLinkImage = getImageReferenceInherited("brandLinkImage");
        }

        return brandLinkImage;

    }

    public String getBrandLinkImageSrc() {

        return getBrandLinkImage().or("");

    }

    public boolean isHasBrandLinkImage() {
        return getBrandLinkImage().isPresent();
    }


    public List<BootstrapMainNavigationElement> getBootstrapMainNavigationElementList() {

        if (bootstrapMainNavigationElementList == null) {

            bootstrapMainNavigationElementList = Lists.newArrayList();

            for (BasicNode currentNavigationNode : getNodesInherited("elements")) {
                LOG.debug("Current Navigation Node Path " + currentNavigationNode.getPath());
                bootstrapMainNavigationElementList.add(currentNavigationNode.getResource().adaptTo(BootstrapMainNavigationElement.class));
            }

        }

		return bootstrapMainNavigationElementList;

	}

}
