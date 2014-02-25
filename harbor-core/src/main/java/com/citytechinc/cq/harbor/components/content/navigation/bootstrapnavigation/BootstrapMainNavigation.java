package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;

import com.citytechinc.cq.harbor.components.content.tree.*;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

import java.util.ArrayList;
import java.util.List;

@Component( value = "Bootstrap Main Navigation",
        group = ".hidden")
@AutoInstantiate( instanceName = "bootstrapMainNavigation" )
public class BootstrapMainNavigation extends AbstractTreeComponent<PageDecorator> {

    @DialogField(ranking = 1)
    @DialogFieldSet( title = "Page Tree Construction Strategy" )
    private final PageTreeConstructionStrategy constructionStrategy;

    private final BootstrapMainNavigationRenderingStrategy renderingStrategy;

    public BootstrapMainNavigation(ComponentRequest request) {
        super(request);

        constructionStrategy = new PageTreeConstructionStrategy(request.getComponentNode());
        renderingStrategy = new BootstrapMainNavigationRenderingStrategy();
    }

    @Override
    protected TreeNodeConstructionStrategy<PageDecorator> getTreeConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected TreeNodeRenderingStrategy<PageDecorator> getTreeRenderingStrategy() {
        return renderingStrategy;
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
}
