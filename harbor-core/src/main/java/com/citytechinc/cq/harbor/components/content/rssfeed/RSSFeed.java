package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.constants.lists.ListConstants;
import com.citytechinc.cq.harbor.services.RSSFeedGeneratorService;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

import java.util.ArrayList;
import java.util.List;

@Component(value = "RSS Feed",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.namespace,harbor.components.content.rssfeed]")
        }, resourceSuperType = AbstractListComponent.RESOURCE_TYPE)
@AutoInstantiate( instanceName = RSSFeed.RSS_FEED_NAME)
public class RSSFeed extends AbstractComponent {

    public static final String RSS_FEED_NAME = "rssFeed";

    public RSSFeed(ComponentRequest request) {
        super(request);
    }

    public RSSFeed(ComponentNode componentNode){
        super(componentNode);
    }

    @DialogField(fieldLabel = "RSS Feed Paths")
    @MultiField
    @PathField
    public final List<String> getRSSUrlList(){
        return getAsList("rSSUrlList", String.class);
    }

    @DialogField(fieldLabel = "Number of Feed Items to display")
    public final int getNumberOfFeedItemsToDisplay(){
        return get("numberOfFeedItemsToDisplay", 10);
    }

    public final String getCurrentRSSFeedPath(){
        return this.getPath();
    }

    @DialogField(fieldLabel = "Update Interval", fieldDescription = "The update interval you want, in seconds")
    public final double getUpdateInterval(){
        return get("updateInterval", 10) * 1000;
    }



}
