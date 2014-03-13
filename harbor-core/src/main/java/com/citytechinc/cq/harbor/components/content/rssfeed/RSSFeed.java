package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.components.content.list.RenderableListItem;
import com.citytechinc.cq.harbor.constants.lists.ListConstants;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

import java.util.List;

@Component(value = "RSS Feed",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.bootstrap]"),
        })
@AutoInstantiate()
public class RSSFeed extends AbstractListComponent<RSSFeedItem> {
    @DialogField
    @DialogFieldSet( title = "List Construction" )
    private final RSSFeedListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet( title = "List Rendering" )
    private final RSSFeedRenderingStrategy renderingStrategy;

    public RSSFeed(ComponentRequest request) {
        super(request);
        constructionStrategy = new RSSFeedListConstructionStrategy(request.getComponentNode());
        renderingStrategy = new RSSFeedRenderingStrategy(request.getComponentNode());
    }

    @Override
    protected ListConstructionStrategy<RSSFeedItem> getListConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<RSSFeedItem> getListRenderingStrategy() {
        return renderingStrategy;
    }

    @Override
    public Boolean getIsUnorderedList() {
        return true;
    }
}
