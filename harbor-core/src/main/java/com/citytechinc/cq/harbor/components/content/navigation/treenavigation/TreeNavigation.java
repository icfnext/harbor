package com.citytechinc.cq.harbor.components.content.navigation.treenavigation;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.tree.AbstractTreeComponent;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Tree Navigation",
        resourceSuperType = AbstractTreeComponent.RESOURCE_TYPE
)
@AutoInstantiate( instanceName = "treeComponent" )
public class TreeNavigation extends AbstractTreeComponent<PageDecorator> {
    @DialogField
    @DialogFieldSet( title = "Tree Construction" )
    private final AbstractTreeConstructionStrategy constructionStrategy;

    private final AbstractTreeRenderingStrategy renderingStrategy;


    public TreeNavigation(ComponentRequest request) {
        super(request);
        constructionStrategy = new AbstractTreeConstructionStrategy(request.getComponentNode());
        renderingStrategy = new AbstractTreeRenderingStrategy();

    }

    @Override
    protected TreeNodeConstructionStrategy<PageDecorator> getTreeConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected TreeNodeRenderingStrategy<PageDecorator> getTreeRenderingStrategy() {
        return renderingStrategy;
    }
}
