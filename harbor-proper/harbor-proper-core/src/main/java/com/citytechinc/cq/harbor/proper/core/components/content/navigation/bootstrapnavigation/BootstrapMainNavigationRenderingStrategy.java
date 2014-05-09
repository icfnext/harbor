package com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation;


import com.citytechinc.cq.harbor.proper.api.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.core.components.content.navigation.Util;
import com.citytechinc.cq.harbor.proper.core.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.proper.core.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class BootstrapMainNavigationRenderingStrategy implements TreeNodeRenderingStrategy<PageDecorator> {

    private String getDropdownToggleTitle(TreeNode<PageDecorator> n){
        StringBuilder nodeTitle = new StringBuilder();

        nodeTitle.append("<" + Elements.A + " href=\"#\" class=\"" + Bootstrap.DROPDOWN_TOGGLE + "\" data-toggle=\"" + Bootstrap.DROPDOWN + "\">");
        nodeTitle.append(Util.getNodeLinkText(n));
        nodeTitle.append("<" + Elements.B + " class=\"caret\"></" + Elements.B + ">");
        nodeTitle.append("</" + Elements.A + ">");

        return nodeTitle.toString();
    }

    private String getNodeTitle(TreeNode<PageDecorator> n){
        StringBuilder nodeTitle = new StringBuilder();

        nodeTitle.append("<" + Elements.A + " href=" + n.getValue().getHref() + ">");
        nodeTitle.append(Util.getNodeLinkText(n));
        nodeTitle.append("</" + Elements.A + ">");

        return nodeTitle.toString();
    }

    private String getListItemTagWithChildren(){
        return "<" + Elements.LI + " class=\"" + Bootstrap.DROPDOWN + "\">";
    }

    private String getListItemTag(){
        return "<" + Elements.LI + ">";
    }

    private String getUnorderedListTag(){
        return "<" + Elements.UL + " class=\"" + Bootstrap.DROPDOWN_MENU + "\">";
    }

    @Override
    public String renderNodeValue(TreeNode<PageDecorator> treeNode) {

        StringBuffer renderedElement = new StringBuffer();

        if(treeNode.getChildren().size() != 0){
            renderedElement.append(getListItemTagWithChildren());
            /*
                Render a "title" to the list,
                then render children underneath
             */
            renderedElement.append(getDropdownToggleTitle(treeNode));
            renderedElement.append(renderChildren(treeNode));
        }

        else{
            renderedElement.append(getListItemTag());
            /*
                Render as straight text
             */
            renderedElement.append(getNodeTitle(treeNode));
        }

        renderedElement.append("</" + Elements.LI + ">");
        return renderedElement.toString();
    }

    @Override
    public String renderChildren(TreeNode<PageDecorator> treeNode) {

        StringBuffer renderedChildrenList = new StringBuffer();

        renderedChildrenList.append(getUnorderedListTag());

        for (TreeNode<PageDecorator> curChild : treeNode.getChildren()) {
            renderedChildrenList.append(renderNodeValue(curChild));
        }

        renderedChildrenList.append("</" + Elements.UL + ">");

        return renderedChildrenList.toString();

    }
}
