package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;

import com.citytechinc.cq.harbor.components.content.tree.*;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component( value = "Bootstrap Main Auto Navigation",
        group = "Harbor Scaffolding",
        actions = {"text:Bootstrap Main Auto Navigation", "-", "edit", "-", "delete"},
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys"
)
@AutoInstantiate( instanceName = "bootstrapMainAutoNavigation" )
public class BootstrapMainAutoNavigation extends AbstractTreeComponent<PageDecorator> {

    @DialogField(ranking = 1)
    @DialogFieldSet( title = "Page Tree Construction Strategy" )
    private final PageTreeConstructionStrategy constructionStrategy;

    private final BootstrapMainNavigationRenderingStrategy renderingStrategy;
    private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    public BootstrapMainAutoNavigation(ComponentRequest request) {
        super(request);

        constructionStrategy = new PageTreeConstructionStrategy(request.getComponentNode());
        renderingStrategy = new BootstrapMainNavigationRenderingStrategy();


    }

    @DialogField(fieldLabel = "Enable Sticky Navigation?",
            fieldDescription = "")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getStickyNavigationEnabled(){
        return get("stickyNavigationEnabled", "").equals("true");
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
