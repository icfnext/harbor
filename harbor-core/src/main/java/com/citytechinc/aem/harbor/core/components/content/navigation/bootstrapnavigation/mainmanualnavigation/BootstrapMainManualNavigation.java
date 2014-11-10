package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import java.util.List;

import com.citytechinc.aem.bedrock.api.link.Link;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.link.builders.factory.LinkBuilderFactory;
import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;

@Component(
        value = "Main Manual Navigation",
        group = ComponentGroups.HARBOR_NAVIGATION,
        actions = {
	        "text:Main Manual Navigation", "-", "edit", "-", "delete" },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.components.content.bootstrapmainmanualnavigation,harbor.bootstrap.navbar]") },
        actionConfigs = {
	        @ActionConfig(xtype = "tbseparator"),
	        @ActionConfig(text = "Add Navigation Item", handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"),
        },
        listeners = { @Listener(name = "afterinsert", value = "REFRESH_PAGE") },
        allowedParents = "*/parsys",
        path = "content/navigation")
@AutoInstantiate(instanceName = "bootstrapMainManualNavigation")
public class BootstrapMainManualNavigation extends AbstractComponent {

	private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    @DialogField(fieldLabel = "Enable Sticky Navigation?", fieldDescription = "")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private Boolean isSticky;

    @DialogField(fieldLabel = "Show Brand Link", fieldDescription = "Enable this to display a link to the root path as the first navigation element")
    @Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
    private Boolean showBrandLink;

    @DialogField(fieldLabel = "Brand Link Text", fieldDescription = "Text to present as the brand of the navigation bar")
    private String brandLinkText;

    @DialogField(fieldLabel = "Brand Link", fieldDescription = "The page or URL the brand should link to")
    @PathField
    private Link brandLink;

    @DialogField(fieldLabel = "Brand Link Image")
    @Html5SmartFile(fileNameParameter = "./fileName", fileReferenceParameter = "./fileReference")
    private Optional<String> brandLinkImage;

	public Boolean getIsSticky() {
        if (isSticky == null) {
            isSticky = getInherited("isSticky", false);
        }

        return isSticky;
	}

    public Boolean getShowBrandLink() {
        if (showBrandLink == null) {
            //TODO: Refactor this and all these properties names into the bootstrap constants file or the properties file
            showBrandLink = getInherited("showBrandLink", false);
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
            Resource brandLinkImageResource = getResource().getChild("brandLinkImage");

            // TODO: presumably getImageSource should be usable for this purpose
            // but for some reason I can't get an instance of the Image class to
            // return true for hasContent
            if (brandLinkImageResource != null) {
                brandLinkImage = Optional.of(brandLinkImageResource.getPath() + ".img.png");
            } else {
                brandLinkImage = Optional.absent();
            }
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

            for (ComponentNode currentComponentNode : getComponentNodes("elements")) {
                bootstrapMainNavigationElementList.add(getComponent(currentComponentNode, BootstrapMainNavigationElement.class));
            }

        }

		return bootstrapMainNavigationElementList;

	}

}
