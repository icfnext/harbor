package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.content.page.navigation.NavigablePage;
import com.icfolson.aem.harbor.api.trees.rendering.TreeRenderingStrategy;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class BootstrapMainNavigationRenderingStrategy extends AbstractComponent implements
    TreeRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableTree> {

    @DialogField(fieldLabel = "Enable Sticky Navigation",
        fieldDescription = "Disabled in Edit mode. Use Preview mode to view.", ranking = 0)
    @Switch(offText = "No", onText = "Yes")
    public Boolean isSticky() {
        return getInherited("sticky", false); // && WCMMode.EDIT != wcmMode;
    }

    @DialogField(fieldLabel = "Brand Link",
        fieldDescription = "Present a brand link as the first element of the navigation.", ranking = 10)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Show", value = "show"),
        @Option(text = "Hide", value = "hide")
    })
    public Boolean getShowBrandLink() {
        return getInherited("showBrandLink", "hide").equals("show");
    }

    @DialogField(fieldLabel = "Brand Link Text",
        fieldDescription = "Text to present as the brand of the navigation bar", ranking = 20)
    @TextField
    public String getBrandLinkText() {
        return getInherited("brandLinkText", "");
    }

    @DialogField(fieldLabel = "Brand Link Image", fieldDescription = "Brand Link Image",
        ranking = 30)
    @Html5SmartImage(tab = false, allowUpload = false, uploadUrl = "", title = "Drag & Drop Image")
    public Optional<String> getBrandLinkImage() {
        return getImageReferenceInherited("brandLinkImage");
    }

    @DialogField(fieldLabel = "Full Width", ranking = 40)
    @Switch(offText = "No", onText = "Yes")
    public Boolean getFullWidth() {
        return getInherited("fullWidth", false);
    }

    @DialogField(fieldLabel = "Present Main Navigation Item in Drop Down",
        fieldDescription = "In cases of nested navigation elements setting this property to true will render the parent navigation element in the context of the drop down and enable expansion of the dropdown via clicking on the entire navigation element.  Setting this property to false will cause a separate drop down icon to render which must be clicked in order to expand the dropdown.",
        ranking = 50)
    @Switch(offText = "No", onText = "Yes")
    public Boolean getPresentMainNavigationItemInDropDown() {
        return getInherited("presentMainNavigationItemInDropDown", false);
    }

    @Override
    public BootstrapPageNavigableRenderableTree toRenderableTree(NavigablePage rootedItem) {
        return new BootstrapPageNavigableRenderableTree(isSticky(), getShowBrandLink(), getBrandLinkText(),
            getBrandLinkImage(), rootedItem, getFullWidth(), getPresentMainNavigationItemInDropDown());
    }
}
