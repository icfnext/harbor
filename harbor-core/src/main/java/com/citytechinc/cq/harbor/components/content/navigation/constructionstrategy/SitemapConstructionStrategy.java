package com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy;

import com.citytechinc.cq.harbor.components.content.tree.TreeNode;
import com.citytechinc.cq.harbor.components.content.tree.TreeNodeConstructionStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import org.apache.sling.api.SlingHttpServletRequest;

public class SitemapConstructionStrategy implements TreeNodeConstructionStrategy<PageDecorator> {
    public SitemapConstructionStrategy(SlingHttpServletRequest request) {

    }

    @Override
    public TreeNode<PageDecorator> construct() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
