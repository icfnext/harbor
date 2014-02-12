package com.citytechinc.cq.harbor.components.content.navigation.treenavigation;


import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.harbor.constants.dom.Elements;
import com.citytechinc.cq.library.content.page.PageDecorator;

public  class TreeRenderingStrategy implements TreeNodeRenderingStrategy<PageDecorator> {

    //TODO: Render arbitrary depth


    protected String getNodeTitle(TreeNode<PageDecorator> n){
        if (n.getValue().getPageTitleOptional().isPresent()) {
            return n.getValue().getPageTitle();
        }

        return n.getValue().getTitle();
    }

    protected String getListItemTag(){
        return "<" + Elements.LI + ">";
    }

    protected String getListItemTagWithChildren(){
        return getListItemTag();
    }

    protected String getUnorderedListTag(){
        return "<" + Elements.UL + ">";
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
            renderedElement.append(getNodeTitle(treeNode));
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
