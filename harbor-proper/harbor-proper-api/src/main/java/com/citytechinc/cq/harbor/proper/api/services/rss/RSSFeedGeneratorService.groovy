package com.citytechinc.cq.harbor.proper.api.services.rss

import com.citytechinc.cq.harbor.proper.api.content.rss.RSSFeed
import com.google.common.base.Optional

public interface RSSFeedGeneratorService {

    public Optional<RSSFeed> getRSSFeed(String feedUrl);

}
