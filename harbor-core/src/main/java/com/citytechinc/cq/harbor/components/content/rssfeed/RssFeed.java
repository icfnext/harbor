package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;


public class RssFeed extends AbstractListComponent {

    public RssFeed(ComponentNode componentNode) {
        super(componentNode);
    }

    public RssFeed(ComponentRequest request) {
        super(request);
    }

    @Override
    protected ListConstructionStrategy getListConstructionStrategy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected ListRenderingStrategy getListRenderingStrategy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
