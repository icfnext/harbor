package com.icfolson.aem.harbor.api.services.rss

import com.google.common.base.Optional
import com.icfolson.aem.harbor.api.content.rss.RSSFeed

public interface RSSFeedGeneratorService {

    Optional<RSSFeed> getRSSFeed(String feedUrl);

}
