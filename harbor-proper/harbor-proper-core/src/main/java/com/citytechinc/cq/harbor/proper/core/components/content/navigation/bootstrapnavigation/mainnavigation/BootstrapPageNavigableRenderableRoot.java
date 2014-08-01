package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation;

import com.citytechinc.cq.harbor.proper.api.content.page.navigation.NavigablePage;
import com.citytechinc.cq.harbor.proper.api.lists.RootedItems;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

public class BootstrapPageNavigableRenderableRoot implements RootedItems<NavigablePage> {

    private final Boolean isSticky;

    private final Boolean showBrandLink;

    private final String brandLinkText;

    private final NavigablePage rootPage;

    private final Optional<String> brandLinkImage;

    public BootstrapPageNavigableRenderableRoot(Boolean sticky, Boolean showBrandLink, String brandLinkText, Optional<String> brandLinkImage, NavigablePage rootPage) {
        this.isSticky = sticky;
        this.showBrandLink = showBrandLink;
        this.brandLinkText = brandLinkText;
        this.rootPage = rootPage;
        this.brandLinkImage = brandLinkImage;
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

        return rootPage.getLink().getTitle();
    }

    @Override
    public Iterable<NavigablePage> getItems() {
        return rootPage.getItems();
    }

    @Override
    public boolean getHasItems() {
        return rootPage.getHasItems();
    }

    @Override
    public Iterator<NavigablePage> getItemsIterator() {
        return getItems().iterator();
    }

    public String getHref() {
        return rootPage.getHref();
    }

    public String getBrandLinkImage() {
        return brandLinkImage.or(StringUtils.EMPTY);
    }

    public boolean isHasBrandLinkImage() {
        return brandLinkImage.isPresent();
    }
}
