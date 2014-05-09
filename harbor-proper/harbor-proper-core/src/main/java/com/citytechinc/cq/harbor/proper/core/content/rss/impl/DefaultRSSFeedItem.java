package com.citytechinc.cq.harbor.proper.core.content.rss.impl;

import com.citytechinc.cq.harbor.proper.api.content.rss.RSSFeedItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultRSSFeedItem implements RSSFeedItem {

    private String title;
    private String link;
    private String pubDate;
    private String description;
    private String HTML;

    public DefaultRSSFeedItem(String title, String link, String pubDate, String description, String HTML) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description = description;
        this.HTML = HTML;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("pubdate")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("HTML")
    public String getHTML() {
        return HTML;
    }
}
