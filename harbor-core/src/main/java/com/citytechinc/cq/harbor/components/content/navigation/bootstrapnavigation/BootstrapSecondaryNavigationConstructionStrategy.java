package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;


import com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodes;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

public class BootstrapSecondaryNavigationConstructionStrategy extends PageTreeConstructionStrategy{
    public BootstrapSecondaryNavigationConstructionStrategy(ComponentNode componentNode) {
        super(componentNode);
    }

    @Override
    protected TreeNode<PageDecorator> getTreeNode(PageDecorator p){
        return TreeNodes.newNavigationTreeNode(p);
    }

    @Override
    protected TreeNode<PageDecorator> getTreeNode(PageDecorator p, List<TreeNode<PageDecorator>> p_list ){
        return TreeNodes.newNavigationTreeNode(p, p_list);
    }
}
