package com.citytechinc.cq.harbor.components.content.navigation.globalnavigation.tree;

import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.harbor.constants.dom.Elements;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class GlobalNavigationTreeRenderingStrategy implements TreeNodeRenderingStrategy<PageDecorator> {

    public GlobalNavigationTreeRenderingStrategy(){

    }

    @Override
    public String renderNodeValue(TreeNode<PageDecorator> treeNode) {
        if (treeNode.getValue().getPageTitleOptional().isPresent()) {
            return treeNode.getValue().getPageTitle();
        }

        return treeNode.getValue().getTitle();
    }

    @Override
    public String renderChildren(TreeNode<PageDecorator> treeNode) {

        StringBuffer renderedChildrenList = new StringBuffer();

        renderedChildrenList.append("<" + Elements.UL + ">");

        for (TreeNode<PageDecorator> curChild : treeNode.getChildren()) {
            renderedChildrenList.append("<" + Elements.LI + ">");
            renderedChildrenList.append(renderNodeValue(curChild));
            renderedChildrenList.append("</" + Elements.LI + ">");
        }

        renderedChildrenList.append("</" + Elements.UL + ">");

        return renderedChildrenList.toString();
    }
}
