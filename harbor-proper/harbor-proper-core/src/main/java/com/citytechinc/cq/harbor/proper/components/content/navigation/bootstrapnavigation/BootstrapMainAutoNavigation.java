package com.citytechinc.cq.harbor.proper.components.content.navigation.bootstrapnavigation;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;

import com.citytechinc.cq.harbor.proper.components.content.tree.*;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.citytechinc.cq.harbor.proper.components.content.navigation.Util;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.List;

@Component( value = "Bootstrap Main Auto Navigation",
        group = "Harbor Scaffolding",
        actions = {"text:Bootstrap Main Auto Navigation", "-", "edit", "-", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.bootstrap.navbar")
        },
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
        return getInherited("stickyNavigationEnabled", "").equals("true");
    }

    @DialogField(fieldLabel = "Show Home Link?",
            fieldDescription = "Enable this to display a link to the root path as the first navigation element")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getHomeLinkEnabled(){
        return getInherited("homeLinkEnabled", "").equals("true");
    }

    public String getId(){
        return this.getPath().split(":")[1].replaceAll("/", "-");
    }

    public String getBrandLinkTarget(){
        return getRootNode().getValue().getHref();
    }

    public String getBrandLinkText(){
        return Util.getNodeLinkText(getRootNode());
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
