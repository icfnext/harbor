package com.citytechinc.cq.harbor.proper.components.content.tree.demo;

import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.harbor.proper.constants.dom.Elements;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class ChildPageTreeRenderingStrategy implements TreeNodeRenderingStrategy<PageDecorator> {

    public ChildPageTreeRenderingStrategy(ComponentNode componentNode) {

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
