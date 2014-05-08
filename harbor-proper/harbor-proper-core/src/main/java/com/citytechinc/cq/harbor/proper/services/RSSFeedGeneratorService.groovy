package com.citytechinc.cq.harbor.proper.services

import com.citytechinc.cq.harbor.proper.components.content.rssfeed.RSSFeedItem;
public interface RSSFeedGeneratorService {
    public List<RSSFeedItem> getListOfRSSFeedItemsFromUrls(List<String> urlList, int numberOfItemsToDisplay);
}
