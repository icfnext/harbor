package com.citytechinc.cq.harbor.components.content.navigation.globalnavigation;

import com.citytechinc.cq.harbor.components.content.navigation.treenavigation.TreeNavigation;
import com.citytechinc.cq.harbor.components.content.navigation.treenavigation.TreeRenderingStrategy;
import com.citytechinc.cq.harbor.components.content.tree.RenderableTreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.constants.dom.Elements;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

import java.util.ArrayList;
import java.util.List;

public class AbstractGlobalNavigation extends TreeNavigation{
    public AbstractGlobalNavigation(ComponentRequest request) {
        super(request);
    }

    public List<TreeNode<PageDecorator>> getRootChildren(){
        if(getRootNode() != null){
            return getRootNode().getChildren();
        }
        return null;
    }

    public List<RenderableTreeNode<PageDecorator>> getRootChildrenAsRenderable(){
        List<RenderableTreeNode<PageDecorator>> out = new ArrayList();
        if(getRootNode() != null){
            for(TreeNode<PageDecorator> node : getRootChildren()){
                out.add(new RenderableTreeNode<PageDecorator>(node, getTreeRenderingStrategy()));
            }
            return out;
        }
        return null;
    }

    /*
        Here we override the Rendering Strategy in order
        to render the nested lists as global navigation elements
     */
    @Override
    protected TreeRenderingStrategy CreateRenderingStrategy(){

        return new TreeRenderingStrategy(){
            @Override
            protected String getNodeTitle(TreeNode<PageDecorator> n){
                Boolean hasChildren = n.getChildren().size() != 0;
                StringBuilder nodeTitle = new StringBuilder();

                //if n has children, render as dropdown trigger
                if(hasChildren){
                    nodeTitle.append("<" + Elements.A + " href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">");
                }
                //otherwise, render as hyperlink
                else{
                    nodeTitle.append("<" + Elements.A + " href=" + n.getValue().getHref() + ">");
                }


                if (n.getValue().getPageTitleOptional().isPresent()) {
                    nodeTitle.append(n.getValue().getPageTitle());
                }

                nodeTitle.append(n.getValue().getTitle());

                //close link
                if(hasChildren){
                    nodeTitle.append("<" + Elements.B + "class=\"caret\"></" + Elements.B + ">");
                }
                else{
                    nodeTitle.append("</" + Elements.A +">");
                }

                return nodeTitle.toString();
            }

            @Override
            protected String getListItemTagWithChildren(){
                return "<" + Elements.LI + " class=\"dropdown\">";
            }

            @Override
            protected String getUnorderedListTag(){
                return "<" + Elements.UL + " class=\"dropdown-menu\">";
            }
        };
    }
}
