package com.citytechinc.cq.harbor.proper.components.content.tree.demo;


import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNodes;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

public class ChildPageTreeConstructionStrategy implements TreeNodeConstructionStrategy<PageDecorator> {

    @DialogField( fieldLabel = "Root Page", name = "./rootPagePath" )
    @PathField
    private final Optional<PageDecorator> rootPageOptional;

    public ChildPageTreeConstructionStrategy(ComponentNode componentNode) {

        Optional<String> rootPagePathOptional = componentNode.get("rootPagePath", String.class);

        if (rootPagePathOptional.isPresent()) {
            PageManagerDecorator pageManagerDecorator = componentNode.getResource().getResourceResolver().adaptTo(PageManagerDecorator.class);
            rootPageOptional = Optional.fromNullable(pageManagerDecorator.getContainingPage(rootPagePathOptional.get()));
        }
        else {
            rootPageOptional = Optional.absent();
        }

    }

    @Override
    public TreeNode<PageDecorator> construct() {

        if (rootPageOptional.isPresent()) {
            List<TreeNode<PageDecorator>> childPages = Lists.newArrayList();

            List<PageDecorator> childPageDecorators = rootPageOptional.get().getChildren();

            for (PageDecorator curChildPage : childPageDecorators) {
                childPages.add(TreeNodes.newBasicTreeNode(curChildPage));
            }

            return TreeNodes.newBasicTreeNode(rootPageOptional.get(), childPages);
        }

        return null;

    }
}
