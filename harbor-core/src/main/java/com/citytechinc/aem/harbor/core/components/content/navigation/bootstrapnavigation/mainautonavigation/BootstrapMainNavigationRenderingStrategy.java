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
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class BootstrapMainNavigationRenderingStrategy extends AbstractComponent implements
        TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> {

	@DialogField(fieldLabel = "Enable Sticky Navigation", fieldDescription = "Disabled in Edit mode. Use Preview mode to view.", ranking = 0)
	@Selection(type = Selection.CHECKBOX, options = { @Option(text = "", value = "true") })
	private Boolean isSticky;

	@DialogField(fieldLabel = "Brand Link", fieldDescription = "Present a brand link as the first element of the navigation.", ranking = 10)
	@Selection(type = Selection.SELECT, options = {
			@Option(text = "Show", value = "show"),
			@Option(text = "Hide", value = "hide")})
	private Boolean showBrandLink;

	@DialogField(fieldLabel = "Brand Link Text", fieldDescription = "Text to present as the brand of the navigation bar", ranking = 20)
	private String brandLinkText;

	@DialogField(fieldLabel = "Brand Link Image", ranking = 30)
	@Html5SmartFile(ddGroups = "media", name="./brandLinkImage", fileNameParameter = "./brandLinkImage/fileName", fileReferenceParameter = "./brandLinkImage/fileReference")
	private Optional<String> brandLinkImage;

    @DialogField(fieldLabel = "Full Width", ranking = 40)
    @Selection(
            type = Selection.CHECKBOX,
            options = @Option(text = "Yes", value = "true")
    )
    private Boolean fullWidth;

    @DialogField(fieldLabel = "Present Main Navigation Item in Drop Down", fieldDescription = "In cases of nested navigation elements setting this property to true will render the parent navigation element in the context of the drop down and enable expansion of the dropdown via clicking on the entire navigation element.  Setting this property to false will cause a separate drop down icon to render which must be clicked in order to expand the dropdown.", ranking = 50)
    @Selection(
            type = Selection.CHECKBOX,
            options = @Option(text = "Yes", value = "true")
    )
    private Boolean presentMainNavigationItemInDropDown;

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

    public Boolean getFullWidth() {
        if (fullWidth == null) {
            fullWidth = getInherited("fullWidth", false);
        }

        return fullWidth;
    }

    public Boolean getPresentMainNavigationItemInDropDown() {
        if (presentMainNavigationItemInDropDown == null) {
            presentMainNavigationItemInDropDown = getInherited("presentMainNavigationItemInDropDown", false);
        }

        return presentMainNavigationItemInDropDown;
    }

	@Override
	public BootstrapPageNavigableRenderableTree toRenderableTree(NavigablePage rootedItem) {
		return new BootstrapPageNavigableRenderableTree(getIsSticky(), getShowBrandLink(), getBrandLinkText(),
			getBrandLinkImage(), rootedItem, getFullWidth(), getPresentMainNavigationItemInDropDown());
	}

}
