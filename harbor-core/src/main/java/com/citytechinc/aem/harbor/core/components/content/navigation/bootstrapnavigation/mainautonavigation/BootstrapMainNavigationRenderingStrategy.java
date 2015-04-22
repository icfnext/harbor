package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.google.common.base.Optional;

public class BootstrapMainNavigationRenderingStrategy extends AbstractComponent implements
        TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> {

	@DialogField(fieldLabel = "Enable Sticky Navigation", fieldDescription = "Disabled in Edit mode. Use Preview mode to view.")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	private Boolean isSticky;

	@DialogField(fieldLabel = "Brand Link", fieldDescription = "Present a brand link as the first element of the navigation.")
	@Selection(type = Selection.SELECT, options = {
			@Option(text = "Show", value = "show"),
			@Option(text = "Hide", value = "hide")})
	private Boolean showBrandLink;

	@DialogField(fieldLabel = "Brand Link Text", fieldDescription = "Text to present as the brand of the navigation bar")
	private String brandLinkText;

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

	public Optional<String> getBrandLinkImage() {
		if (brandLinkImage == null) {
			brandLinkImage = getImageReferenceInherited("brandLinkImage");
		}

		return brandLinkImage;
	}

	@Override
	public BootstrapPageNavigableRenderableTree toRenderableTree(NavigablePage rootedItem) {
		return new BootstrapPageNavigableRenderableTree(getIsSticky(), getShowBrandLink(), getBrandLinkText(),
			getBrandLinkImage(), rootedItem);
	}
}
