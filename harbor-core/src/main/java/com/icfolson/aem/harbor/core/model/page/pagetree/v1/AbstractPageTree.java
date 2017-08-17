package com.icfolson.aem.harbor.core.model.page.pagetree.v1;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTree;
import com.icfolson.aem.harbor.api.model.page.pagetree.PageTreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractPageTree<T extends PageTreeNode> implements PageTree<T> {

    private T tree;

    @Override
    public T getRoot() {
        if (tree == null) {
            PageDecorator rootPage = getRootPage();

            if (rootPage == null) {
                return null;
            }

            tree = buildTreeNodeForPage(rootPage, 0);
        }

        return tree;
    }

    @Override
    public boolean isHasRoot() {
        return getRoot() != null;
    }

    public abstract Predicate<PageDecorator> getInclusionPredicate();

    public abstract T transformPageAndChildren(PageDecorator page, List<T> children);

    public abstract PageDecorator getRootPage();

    protected T buildTreeNodeForPage(PageDecorator page, int currentDepth) {
        if (getDepth().isPresent() && currentDepth >= getDepth().get()) {
            return transformPageAndChildren(page, Lists.newArrayList());
        }

        List<T> children = page
                .getChildren(getInclusionPredicate())
                .stream()
                .map(currentPage -> this.buildTreeNodeForPage(currentPage, currentDepth + 1))
                .collect(Collectors.toList());

        return transformPageAndChildren(page, children);
    }

}
