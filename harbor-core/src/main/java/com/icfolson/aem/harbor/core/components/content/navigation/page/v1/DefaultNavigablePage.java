package com.icfolson.aem.harbor.core.components.content.navigation.page.v1;

import com.icfolson.aem.harbor.api.components.content.navigation.page.NavigablePage;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;

public class DefaultNavigablePage implements NavigablePage {

    private final PageDecorator page;
    private final PageDecorator currentPage;

    public DefaultNavigablePage(PageDecorator page) {
        this(page, null);
    }

    public DefaultNavigablePage(PageDecorator page, PageDecorator currentPage) {
        this.page = page;
        this.currentPage = currentPage;
    }

    @Override
    public boolean isRedirect() {
        return getPage().get("redirectTarget", String.class).isPresent();
    }

    @Override
    public boolean isAlongActivePath() {
        return getCurrentPage() != null && getCurrentPage().getPath().indexOf(getPage().getPath()) == 0;
    }

    @Override
    public String getTitle() {
        if (StringUtils.isNotBlank(getPage().getNavigationTitle())) {
            return getPage().getNavigationTitle();
        }
        if (StringUtils.isNotBlank(getPage().getPageTitle())) {
            return getPage().getPageTitle();
        }

        return getPage().getTitle();
    }

    @Override
    public String getHref() {
        if (!isRedirect()) {
            return getPage().getHref();
        }

        return getPage().get("redirectTarget", "#"); //TODO: Deal with redirects to internal pages
    }

    public PageDecorator getPage() {
        return page;
    }

    public PageDecorator getCurrentPage() {
        return currentPage;
    }

}
