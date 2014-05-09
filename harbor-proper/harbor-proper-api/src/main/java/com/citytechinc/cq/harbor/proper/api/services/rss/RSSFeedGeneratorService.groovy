package com.citytechinc.cq.harbor.proper.api.services.rss

import com.citytechinc.cq.harbor.proper.api.content.rss.RSSFeedItem

public interface RSSFeedGeneratorService {

    public List<RSSFeedItem> getListOfRSSFeedItemsFromUrls(List<String> urlList, int numberOfItemsToDisplay);

}
