package com.citytechinc.cq.harbor.services

import com.citytechinc.cq.harbor.components.content.rssfeed.RSSFeedItem;
public interface RSSFeedGeneratorService {
    public List<RSSFeedItem> getListOfRSSFeedItemsFromUrls(List<String> urlList, int numberOfItemsToDisplay);
}
