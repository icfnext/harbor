package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.services.RSSFeedGeneratorService;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.google.common.base.Optional;
import org.apache.felix.scr.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

public class RSSFeedListConstructionStrategy implements ListConstructionStrategy<RSSFeedItem> {



    @DialogField(fieldLabel = "rssFeedPaths", name = "./rssFeedPaths")
    @PathField
    private final Optional<List<String>> RSSUrlListOptional;

    private RSSFeedGeneratorService rssFeedGeneratorService;

    public RSSFeedListConstructionStrategy(ComponentNode componentNode) {
        PageManagerDecorator pageManagerDecorator = componentNode.getResource().getResourceResolver().adaptTo(PageManagerDecorator.class);
        List<String> RSSUrlList = componentNode.getAsList("rssFeedPaths", String.class);
        RSSUrlListOptional = Optional.fromNullable(RSSUrlList);
    }

    @Override
    public List<RSSFeedItem> constructList() {
        List<RSSFeedItem> rssFeedItemList = new ArrayList<RSSFeedItem>();
        if (RSSUrlListOptional.isPresent()) {
            rssFeedItemList = rssFeedGeneratorService.getListOfRSSFeedItemsFromUrls(RSSUrlListOptional.get());
        }
        return rssFeedItemList;
    }

    public void setRssFeedGeneratorService(RSSFeedGeneratorService rssFeedGeneratorService) {
        this.rssFeedGeneratorService = rssFeedGeneratorService;
    }
}
