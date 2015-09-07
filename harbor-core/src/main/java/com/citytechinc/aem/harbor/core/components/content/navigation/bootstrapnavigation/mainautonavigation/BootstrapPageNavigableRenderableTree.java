package com.citytechinc.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainautonavigation;

import com.citytechinc.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.citytechinc.aem.harbor.api.content.page.navigation.NavigablePage;
import com.citytechinc.aem.harbor.api.trees.Tree;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

public class BootstrapPageNavigableRenderableTree implements Tree<NavigablePage> {

    private final Boolean isSticky;

    private final Boolean showBrandLink;

    private final String brandLinkText;

    private final NavigablePage rootPage;

    private final Optional<String> brandLinkImage;

    private final boolean fullWidth;

    public BootstrapPageNavigableRenderableTree(Boolean sticky, Boolean showBrandLink, String brandLinkText, Optional<String> brandLinkImage, NavigablePage rootPage, boolean fullWidth) {
        this.isSticky = sticky;
        this.showBrandLink = showBrandLink;
        this.brandLinkText = brandLinkText;
        this.rootPage = rootPage;
        this.brandLinkImage = brandLinkImage;
        this.fullWidth = fullWidth;
    }

    public Boolean getIsSticky() {
        return isSticky;
    }

    public Boolean getShowBrandLink() {
        return showBrandLink;
    }

    public String getBrandLinkText() {
        if (StringUtils.isNotBlank(brandLinkText)) {
            return brandLinkText;
        }
        else if (rootPage != null) {
            return rootPage.getLink().getTitle();
        }
        else {
            return "No Brand Text Configured";
        }
    }

    public String getHref() {
        if (rootPage != null) {
            return rootPage.getHref();
        }

        return "#";
    }

    public String getBrandLinkImage() {
        return brandLinkImage.or(StringUtils.EMPTY);
    }

    public boolean isHasBrandLinkImage() {
        return brandLinkImage.isPresent();
    }

    @Override
    public NavigablePage getRoot() {
        return rootPage;
    }

    @Override
    public boolean isHasRoot() {
        return rootPage != null;
    }

    public boolean isFullWidth() {
        return fullWidth;
    }

    public String getContainerClass() {
        if (isFullWidth()) {
            return Bootstrap.CONTAINER_FULL_WIDTH_CLASS;
        }

        return Bootstrap.CONTAINER_CLASS;
    }
}
