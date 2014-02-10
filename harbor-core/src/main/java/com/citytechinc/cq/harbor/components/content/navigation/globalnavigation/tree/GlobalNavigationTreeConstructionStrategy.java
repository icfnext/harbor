package com.citytechinc.cq.harbor.components.content.navigation.globalnavigation.tree;

import com.citytechinc.cq.harbor.components.content.tree.DefaultTreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodes;
import com.citytechinc.cq.harbor.content.page.impl.PagePredicates;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Predicate;

import java.util.ArrayList;
import java.util.List;

public class GlobalNavigationTreeConstructionStrategy implements TreeNodeConstructionStrategy<PageDecorator> {
    private static final Predicate<PageDecorator> INCLUDE_ALL_CHILD_PAGE_TYPES = new Predicate<PageDecorator>() {
        @Override
        public boolean apply(PageDecorator page) {
            return PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(page)
                    || PagePredicates.HIERARCHICAL_PAGE_PREDICATE.apply(page);
        }
    };
    private final int depth;
    private final PageDecorator startPage;

    public GlobalNavigationTreeConstructionStrategy(PageDecorator startPage, int depth){
        this.depth = depth;
        this.startPage = startPage;
    }

    protected TreeNode<PageDecorator> BuildNavigationTree(PageDecorator pageRoot){
        //grab child pages from homePage
        TreeNode root = new DefaultTreeNode(pageRoot, pageRoot.getChildren(INCLUDE_ALL_CHILD_PAGE_TYPES));

        return BuildNavigationTreeRecur(root);
    }

    protected TreeNode<PageDecorator> BuildNavigationTreeRecur(TreeNode n){
        //base cases
        PageDecorator page = (PageDecorator) n.getValue();

        if(page.getDepth() > depth){
            return n;
        }
        if(n.getChildren().size() == 0){
            return n;
        }


        List<PageDecorator> child_page_decorators = new ArrayList<PageDecorator>();
        List<TreeNode<PageDecorator>> child_nav_nodes = new ArrayList<TreeNode<PageDecorator>>();

        //populate the temp node's children array
        for(int i = 0; i < child_page_decorators.size(); i++){
            TreeNode temp = BuildNavigationTreeRecur(TreeNodes.newBasicTreeNode(child_page_decorators.get(i)));

            child_nav_nodes.add(temp);
        }

        //return the node temp node with our additions
        TreeNode temp_node = TreeNodes.newBasicTreeNode(page, child_nav_nodes);
        return temp_node;
    }

    @Override
    public TreeNode<PageDecorator> construct() {
        return BuildNavigationTree(this.startPage);
    }
}
