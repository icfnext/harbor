package com.citytechinc.cq.harbor.components.content.navigation.treenavigation;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.components.content.tree.DefaultTreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodes;
import com.citytechinc.cq.harbor.content.page.impl.PagePredicates;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AbstractTreeConstructionStrategy implements TreeNodeConstructionStrategy<PageDecorator> {
    @DialogField( fieldLabel = "Root Page", name = "./rootPagePath" )
    @PathField
    private final Optional<PageDecorator> rootPageOptional;

    @DialogField(fieldLabel = "Depth Level", fieldDescription = "How many levels deep the tree is to be built.", name = "./depthLevel")
    @NumberField
    private final Optional<Integer> depthLevel;

    //TODO predicate selection
    private static final Predicate<PageDecorator> INCLUDE_ALL_CHILD_PAGE_TYPES = new Predicate<PageDecorator>() {
        @Override
        public boolean apply(PageDecorator page) {
            return PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(page)
                    || PagePredicates.HIERARCHICAL_PAGE_PREDICATE.apply(page);
        }
    };

    //TODO: MANUAL? y/n

    private static final Integer DEFAULT_NAV_DEPTH = 1;

    public AbstractTreeConstructionStrategy(ComponentNode componentNode) {

        /*
            Grabs the root page set up in the Tree Navigation Component.
         */
        Optional<String> rootPagePathOptional = componentNode.get("rootPagePath", String.class);

        if (rootPagePathOptional.isPresent()) {
            PageManagerDecorator pageManagerDecorator = componentNode.getResource().getResourceResolver().adaptTo(PageManagerDecorator.class);
            rootPageOptional = Optional.fromNullable(pageManagerDecorator.getContainingPage(rootPagePathOptional.get()));
        }
        else {
            rootPageOptional = Optional.absent();
        }

        /*
            Initialize depth level
         */
        Optional<String> depthLevelRaw = componentNode.get("depthLevel", String.class);
        if(depthLevelRaw.isPresent()){
            depthLevel = Optional.fromNullable(Integer.parseInt(depthLevelRaw.get()));
        }
        else{
            depthLevel = Optional.fromNullable(DEFAULT_NAV_DEPTH);
        }
    }

 /*   @Override
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

    }*/

    protected TreeNode<PageDecorator> BuildNavigationTree(PageDecorator pageRoot){
        //grab child pages from homePage
        TreeNode<PageDecorator> root = new DefaultTreeNode(pageRoot, pageRoot.getChildren(INCLUDE_ALL_CHILD_PAGE_TYPES));


        TreeNode<PageDecorator> new_root = buildTreeRecur(TreeNode<PageDecorator> b, 2);

    }

    private  TreeNode<PageDecorator> buildTreeRecur(TreeNode<PageDecorator> n, int depth){

    }


    @Override
    public TreeNode<PageDecorator> construct() {
        return BuildNavigationTree(this.rootPageOptional.get());
    }
}
