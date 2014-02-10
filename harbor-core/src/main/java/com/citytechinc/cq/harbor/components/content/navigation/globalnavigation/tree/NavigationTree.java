package com.citytechinc.cq.harbor.components.content.navigation.globalnavigation.tree;

import com.citytechinc.cq.harbor.components.content.tree.AbstractTreeComponent;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeRenderingStrategy;
import com.citytechinc.cq.harbor.content.page.impl.DefaultHierarchicalPage;
import com.citytechinc.cq.harbor.content.page.impl.DefaultHomePage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

public class NavigationTree extends AbstractTreeComponent<PageDecorator> {
    private final GlobalNavigationTreeConstructionStrategy treeConstructionStrategy;
    private final GlobalNavigationTreeRenderingStrategy treeRenderingStrategy;


    public NavigationTree(ComponentRequest request, int depth) {
        super(request);
        //Retrieve homepage object out of the current path
        DefaultHierarchicalPage currentPage = new DefaultHierarchicalPage(request.getCurrentPage());
        DefaultHomePage homePage = new DefaultHomePage(currentPage.getHomePage().get());

        treeRenderingStrategy = new GlobalNavigationTreeRenderingStrategy();
        treeConstructionStrategy = new GlobalNavigationTreeConstructionStrategy(homePage, depth);

    }

    @Override
    protected TreeNodeConstructionStrategy<PageDecorator> getTreeConstructionStrategy() {
        return treeConstructionStrategy;
    }

    @Override
    protected TreeNodeRenderingStrategy<PageDecorator> getTreeRenderingStrategy() {
        return treeRenderingStrategy;
    }
}
