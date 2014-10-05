package com.citytechinc.aem.harbor.api.services.rss

import com.citytechinc.aem.harbor.api.content.rss.RSSFeed
import com.google.common.base.Optional

public interface RSSFeedGeneratorService {

    public Optional<RSSFeed> getRSSFeed(String feedUrl);

}
