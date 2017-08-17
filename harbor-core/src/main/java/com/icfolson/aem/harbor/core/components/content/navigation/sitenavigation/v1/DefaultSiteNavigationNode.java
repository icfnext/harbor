package com.icfolson.aem.harbor.core.components.content.navigation.sitenavigation.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTreeNode;
import com.icfolson.aem.harbor.core.model.page.pagetree.v1.DefaultPageTreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

public class DefaultSiteNavigationNode extends DefaultPageTreeNode {

    public DefaultSiteNavigationNode(PageDecorator page, List<PageTreeNode> children) {
        super(page, children);
    }

    public DefaultSiteNavigationNode(PageDecorator page, List<PageTreeNode> children, PageDecorator currentPage) {
        super(page, children, currentPage);
    }

    @Override
    public String getTitle() {
        if (StringUtils.isNotBlank(getValue().getNavigationTitle())) {
            return getValue().getNavigationTitle();
        }

        if (StringUtils.isNotBlank(getValue().getPageTitle())) {
            return getValue().getPageTitle();
        }

        return getValue().getTitle();
    }

}
