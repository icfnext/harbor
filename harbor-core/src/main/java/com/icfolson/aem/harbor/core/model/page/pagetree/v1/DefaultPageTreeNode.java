package com.icfolson.aem.harbor.core.model.page.pagetree.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

public class DefaultPageTreeNode implements PageTreeNode<PageTreeNode> {

    private final PageDecorator page;
    private final List<PageTreeNode> children;
    private final PageDecorator currentPage;

    public DefaultPageTreeNode(PageDecorator page, List<PageTreeNode> children) {
        this.page = page;
        this.children = children;
        this.currentPage = null;
    }

    public DefaultPageTreeNode(PageDecorator page, List<PageTreeNode> children, PageDecorator currentPage) {
        this.page = page;
        this.children = children;
        this.currentPage = currentPage;
    }

    @Override
    public boolean isRedirect() {
        return getValue().get("redirectTarget", String.class).isPresent();
    }

    @Override
    public boolean isAlongActivePath() {
        return this.currentPage != null && page.getPath().indexOf(currentPage.getPath()) == 1;
    }

    @Override
    public String getTitle() {
        if (StringUtils.isNotBlank(getValue().getPageTitle())) {
            return getValue().getPageTitle();
        }

        return getValue().getTitle();
    }

    @Override
    public String getHref() {
        if (!isRedirect()) {
            return getValue().getHref();
        }

        return getValue().get("redirectTarget", "#"); //TODO: Deal with redirects to internal pages
    }

    @Override
    public Iterable<PageTreeNode> getChildNodes() {
        return children != null ? children : Lists.newArrayList();
    }

    @Override
    public boolean isHasChildNodes() {
        return children != null && !children.isEmpty();
    }

    @Override
    public Iterator<PageTreeNode> getChildNodesIterator() {
        return getChildNodes().iterator();
    }

    @Override
    public PageDecorator getValue() {
        return page;
    }

}
