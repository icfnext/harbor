package com.citytechinc.cq.harbor.proper.core.components.content.tree.demo;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.core.components.content.tree.AbstractTreeComponent;
import com.citytechinc.cq.harbor.proper.core.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Page Tree", group = "Harbor Lists", name = "trees/demopagetree", resourceSuperType = AbstractTreeComponent.RESOURCE_TYPE )
@AutoInstantiate( instanceName = "treeComponent" )
public class PageTree extends AbstractTreeComponent <PageDecorator>{

    @DialogField
    @DialogFieldSet( title = "Tree Construction" )
    private final ChildPageTreeConstructionStrategy childPageTreeConstructionStrategy;

    private final ChildPageTreeRenderingStrategy childPageTreeRenderingStrategy;

    public PageTree(ComponentRequest request) {
        super(request);

        childPageTreeConstructionStrategy = new ChildPageTreeConstructionStrategy(request.getComponentNode());
        childPageTreeRenderingStrategy = new ChildPageTreeRenderingStrategy(request.getComponentNode());

    }

    @Override
    protected TreeNodeConstructionStrategy<PageDecorator> getTreeConstructionStrategy() {
        return childPageTreeConstructionStrategy;
    }

    @Override
    protected TreeNodeRenderingStrategy<PageDecorator> getTreeRenderingStrategy() {
        return childPageTreeRenderingStrategy;
    }

}
