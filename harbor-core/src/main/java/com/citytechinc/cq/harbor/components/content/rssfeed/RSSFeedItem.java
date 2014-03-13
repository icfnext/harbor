package com.citytechinc.cq.harbor.components.content.rssfeed;

public class RSSFeedItem {
    public static String PUB_DATE_XML_TAG_NAME = "pubDate";
    public static String TITLE_XML_TAG_NAME = "title";
    public static String LINK_XML_TAG_NAME = "link";
    public static String DESCRIPTION_XML_TAG_NAME = "description";
    private String title;
    private String link;
    private String pubDate;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
