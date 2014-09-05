package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartFile;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.RootedListRenderingStrategy;
import com.google.common.base.Optional;

public class BootstrapMainNavigationRenderingStrategy extends AbstractComponent implements
	RootedListRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableRoot> {

	@DialogField(fieldLabel = "Enable Sticky Navigation", fieldDescription = "")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	private Boolean isSticky;

	@DialogField(fieldLabel = "Show Brand Link", fieldDescription = "Enable this to display a link to the root path as the first navigation element")
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	private Boolean showBrandLink;

	@DialogField(fieldLabel = "Brand Link Text", fieldDescription = "Text to present as the brand of the navigation bar")
	private String brandLinkText;

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

	@Override
	public BootstrapPageNavigableRenderableRoot toRenderableList(NavigablePage rootedItem) {
		return new BootstrapPageNavigableRenderableRoot(getIsSticky(), getShowBrandLink(), getBrandLinkText(),
			getBrandLinkImage(), rootedItem);
	}
}
