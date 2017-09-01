package com.icfolson.aem.harbor.core.components.content.list.page.v1;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;

public class DefaultPageListItem implements ListableLink {

    private final PageDecorator page;

    public DefaultPageListItem(PageDecorator page) {
        this.page = page;
    }

    @Override
    public String getTitle() {
        return StringUtils.isNotBlank(page.getPageTitle()) ? page.getPageTitle() : page.getTitle();
    }

    @Override
    public String getHref() {
        return getRedirectTarget().or(page.getHref());
    }

    @Override
    public boolean isOpenInNewWindow() {
        return getRedirectTarget().isPresent();
    }

    public Optional<String> getRedirectTarget() {
        return page.get("redirectTarget", String.class);
    }
}
