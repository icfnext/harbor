package com.citytechinc.cq.harbor.components.content.tree;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Abstract Tree", group = ".hidden", name = "trees/abstracttree" )
public abstract class AbstractTreeComponent <T> extends AbstractComponent implements TreeComponent <T> {

    public static final String RESOURCE_TYPE = "harbor/components/content/trees/abstracttree";

    private TreeNode<T> rootNode;
    private RenderableTreeNode<T> renderableRootNode;

    public AbstractTreeComponent(ComponentNode componentNode) {
        super(componentNode);
    }

    public AbstractTreeComponent(ComponentRequest request) {
        super(request);
    }

    protected abstract TreeNodeConstructionStrategy<T> getTreeConstructionStrategy();

    protected abstract TreeNodeRenderingStrategy<T> getTreeRenderingStrategy();

    public TreeNode<T> getRootNode() {
        if (rootNode == null) {
            rootNode = getTreeConstructionStrategy().construct();
        }

        return rootNode;
    }

    public RenderableTreeNode<T> getRenderableRootNode() {
        if (renderableRootNode == null) {
            renderableRootNode = new RenderableTreeNode<T>(getRootNode(), getTreeRenderingStrategy());
        }

        return renderableRootNode;
    }

    public Boolean getHasRootNode() {
        return getRootNode() != null;
    }


}
