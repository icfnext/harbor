package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.RootedListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation.BootstrapPageNavigableRenderableRoot;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

public class BootstrapMainNavigationRenderingStrategy extends AbstractComponent implements RootedListRenderingStrategy<NavigablePage, BootstrapPageNavigableRenderableRoot> {

    @DialogField(fieldLabel = "Enable Sticky Navigation",
            fieldDescription = "")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    private Boolean isSticky;

    @DialogField(fieldLabel = "Show Brand Link",
            fieldDescription = "Enable this to display a link to the root path as the first navigation element")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    private Boolean showBrandLink;

    @DialogField(fieldLabel = "Brand Link Text",
            fieldDescription = "Text to present as the brand of the navigation bar")
    private String brandLinkText;

    public BootstrapMainNavigationRenderingStrategy(ComponentRequest request) {
        super(request);
    }

    public Boolean getIsSticky() {
        if (isSticky == null) {
            isSticky = get("isSticky", false);
        }

        return isSticky;
    }

    public Boolean getShowBrandLink() {
        if (showBrandLink == null) {
            showBrandLink = get("showBrandLink", false);
        }

        return showBrandLink;
    }

    public String getBrandLinkText() {
        if (brandLinkText == null) {
            brandLinkText = get("brandLinkText", StringUtils.EMPTY);
        }

        return brandLinkText;
    }

    @Override
    public BootstrapPageNavigableRenderableRoot toRenderableList(NavigablePage rootedItem) {
        return new BootstrapPageNavigableRenderableRoot(getIsSticky(), getShowBrandLink(), getBrandLinkText(), rootedItem);
    }
}
