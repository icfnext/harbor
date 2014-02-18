package com.citytechinc.cq.harbor.components.content.navigation.treenavigation;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
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

import java.util.ArrayList;
import java.util.List;

public class TreeConstructionStrategy implements TreeNodeConstructionStrategy<PageDecorator> {
    private Predicate<PageDecorator> predicate;
    private static final String predicateDefault = "INCLUDE_ALL_CHILD_PAGE_TYPES";
    private static final Integer DEFAULT_NAV_DEPTH = 0;

    @DialogField( fieldLabel = "Root Page", name = "./rootPagePath" )
    @PathField
    private final Optional<PageDecorator> rootPageOptional;

    @DialogField(fieldLabel = "Depth Level", fieldDescription = "How many levels deep the tree is to be built from the root page.", name = "./depthLevel")
    @NumberField
    private final Optional<Integer> depthLevel;

    @DialogField(fieldLabel = "Page Predicate Selection",
            fieldDescription = "Limit what types of pages can be added to the navigation.",
            name = "./predicate")
    @Selection(type = Selection.SELECT, options = {
        @Option(text="Content Page", value="HIERARCHICAL_PAGE_PREDICATE"),
        @Option(text="Section Landing Page", value="SECTION_LANDING_PAGE_PREDICATE"),
        @Option(text="All Child Page Types", value="INCLUDE_ALL_CHILD_PAGE_TYPES"),
    })
    private final Optional<String> predicateString;

    public TreeConstructionStrategy(ComponentNode componentNode) {

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

        /*
            Init Page Predicate
         */
        Optional<String> predicateStringOptional = componentNode.get("predicate", String.class);
        if(predicateStringOptional.isPresent()){
            predicateString = Optional.fromNullable(predicateStringOptional.get());
        }
        else{
            predicateString = Optional.fromNullable(predicateDefault);
        }
    }

    protected TreeNode<PageDecorator> BuildNavigationTree(PageDecorator pageRoot){
        //grab child pages from homePage
        List<PageDecorator> pageRootChildren = pageRoot.getChildren(getPredicate());
        TreeNode<PageDecorator> root = TreeNodes.newBasicTreeNode(pageRoot);

        TreeNode<PageDecorator> new_root = buildTreeRecur(root, 0);

        return new_root;
    }

    private  TreeNode<PageDecorator> buildTreeRecur(TreeNode<PageDecorator> n, int depth){
        if (depth == depthLevel.get()){
            return n;
        }

        List<TreeNode<PageDecorator>> children_of_n = transformListToTreeNodeList(n.getValue().getChildren(getPredicate()));
        List<TreeNode<PageDecorator>> new_children_of_n = new ArrayList();

        //visit step
        for(TreeNode<PageDecorator> child_of_n : children_of_n){
            //grab children from child_of_n.getValue()
            PageDecorator p_temp = child_of_n.getValue();
            List<PageDecorator> p_temp_children = p_temp.getChildren(getPredicate());

            //create new node with same value, and value's children
            TreeNode<PageDecorator> treeNode = TreeNodes.newBasicTreeNode(p_temp, transformListToTreeNodeList(p_temp_children));

            //Recurse, and fill in treenode's children, etc.
            treeNode =  buildTreeRecur(treeNode, depth++);

            //add new node to our child list
            new_children_of_n.add(treeNode);
        }

        //create new node from n, adding in the new child list we create with n's same .getValue() result
        return TreeNodes.newBasicTreeNode(n.getValue(), new_children_of_n);
    }

    private List<TreeNode<PageDecorator>> transformListToTreeNodeList(List<PageDecorator> list) {
        List<TreeNode<PageDecorator>> temp = new ArrayList<TreeNode<PageDecorator>>();
        for(PageDecorator p : list){
            TreeNode<PageDecorator> treeNode = TreeNodes.newBasicTreeNode(p);
            temp.add(treeNode);
        }
        return temp;
    }

    @Override
    public TreeNode<PageDecorator> construct() {
        if (rootPageOptional.isPresent() && predicateString.isPresent()) {
            //snag our predicate
            this.predicate = PagePredicates.PREDICATE_MAP.get(this.predicateString.get());
            return BuildNavigationTree(this.rootPageOptional.get());
        }
        return null;
    }

    public Predicate<PageDecorator> getPredicate() {
        return predicate;
    }
}
