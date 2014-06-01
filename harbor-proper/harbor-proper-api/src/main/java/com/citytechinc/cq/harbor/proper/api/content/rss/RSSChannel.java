package com.citytechinc.cq.harbor.proper.api.content.rss;

import java.net.URI;
import java.util.List;

/**
 * Represents a single RSS Feed Channel
 */
public interface RSSChannel {

    /**
     * Represents the title element of an RSS Channel
     *
     * @return The title element of an RSS Channel
     */
    public String getTitle();

    /**
     * Represents the link element of an RSS Channel
     *
     * @return The link element of an RSS Channel
     */
    public String getLink();

    /**
     * Represents the description element of an RSS Channel
     *
     * @return The description element of an RSS Channel
     */
    public String getDescription();

    /**
     * Represents the items provided in an RSS Channel
     *
     * @return The set of items contained in the RSS Channel ordered as provided
     */
    public List<? extends RSSItem> getItems();


}
