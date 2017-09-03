package com.icfolson.aem.harbor.core.components.content.navigation.page.v1;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.navigation.page.NavigablePage;
import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.api.datastructure.tree.TreeNode;
import com.icfolson.aem.harbor.core.content.page.impl.PagePredicates;
import com.icfolson.aem.harbor.core.trees.v1.DefaultTreeNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = TreeComponent.class, resourceType = DefaultNavigablePageTree.RESOURCE_TYPE)
public class DefaultNavigablePageTree implements TreeComponent<NavigablePage> {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/navigablepagetree/v1/navigablepagetree";

    private TreeNode<NavigablePage> tree;

    @Inject
    private PageDecorator currentPage;

    @Override
    public TreeNode<NavigablePage> getTree() {
        if (tree == null) {
            PageDecorator rootPage = getRootPage();

            if (rootPage == null) {
                return null;
            }

            tree = buildTreeNodeForPage(rootPage, 0);
        }

        return tree;
    }

    public Predicate<PageDecorator> getInclusionPredicate() {
        return PagePredicates.NAVIGABLE_PAGES_PREDICATE;
    }

    public TreeNode<NavigablePage> transformPageAndChildren(PageDecorator page, List<TreeNode<NavigablePage>> children, int depth) {
        return new DefaultTreeNode<>(new DefaultNavigablePage(page, currentPage), children, depth);
    }

    public PageDecorator getRootPage() {
        return currentPage;
    }

    public Optional<Integer> getDepth() {
        return Optional.empty();
    }

    protected TreeNode<NavigablePage> buildTreeNodeForPage(PageDecorator page, int currentDepth) {
        if (getDepth().isPresent() && currentDepth >= getDepth().get()) {
            return transformPageAndChildren(page, Lists.newArrayList(), currentDepth);
        }

        List<TreeNode<NavigablePage>> children = page
                .getChildren(getInclusionPredicate())
                .stream()
                .map(currentPage -> this.buildTreeNodeForPage(currentPage, currentDepth + 1))
                .collect(Collectors.toList());

        return transformPageAndChildren(page, children, currentDepth);
    }

}
