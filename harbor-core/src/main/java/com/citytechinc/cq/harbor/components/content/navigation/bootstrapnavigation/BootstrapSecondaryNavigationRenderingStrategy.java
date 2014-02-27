package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;

import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodes;
import com.citytechinc.cq.harbor.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.constants.dom.Elements;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

public class BootstrapSecondaryNavigationRenderingStrategy implements TreeNodeRenderingStrategy<PageDecorator> {
    ComponentRequest request;
    PageDecorator activePage;

    public BootstrapSecondaryNavigationRenderingStrategy(ComponentRequest request) {
        request = request;
        activePage = request.getCurrentPage();
    }

    private Boolean isNodeTheCurrentPage(TreeNode<PageDecorator> node){
        if(node.getValue().getPath() == activePage.getPath()){
            return true;
        }
        return false;
    }

    private Boolean isChildNodeTheCurrentPage(TreeNode<PageDecorator> node){
        for(TreeNode<PageDecorator> child : node.getChildren()){
            if (isNodeTheCurrentPage(child)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String renderNodeValue(TreeNode<PageDecorator> treeNode) {
        StringBuffer nodeValue = new StringBuffer();

        Boolean renderChildren  = isChildNodeTheCurrentPage(treeNode);
        Boolean currentNodeActive = isNodeTheCurrentPage(treeNode);

        if (currentNodeActive){
            nodeValue.append("<" + Elements.LI + " class = \"" + Bootstrap.ACTIVE + "\">");
        }
        else{
            nodeValue.append("<" + Elements.LI + ">");
        }

        nodeValue.append("<" + Elements.A + " href=" + treeNode.getValue().getHref() + ">");

        if (treeNode.getValue().getPageTitleOptional().isPresent()) {
            nodeValue.append(treeNode.getValue().getPageTitle());
        }
        else{
            nodeValue.append(treeNode.getValue().getTitle());
        }

        nodeValue.append("</" + Elements.A +">");


        if(currentNodeActive){
            nodeValue.append(renderChildren(treeNode));
        }
        else if(renderChildren){
            nodeValue.append(renderChildren(treeNode));
        }


        nodeValue.append("</" + Elements.LI + ">");
        return nodeValue.toString();
    }

    @Override
    public String renderChildren(TreeNode<PageDecorator> treeNode) {

        StringBuffer renderedChildrenList = new StringBuffer();

        renderedChildrenList.append("<" + Elements.UL + " class=\"" + Bootstrap.NAV_PILLS_STACKED + "\">");

        for (TreeNode<PageDecorator> curChild : treeNode.getChildren()) {
            renderedChildrenList.append(renderNodeValue(curChild));
        }

        renderedChildrenList.append("</" + Elements.UL + ">");

        return renderedChildrenList.toString();

    }
}
