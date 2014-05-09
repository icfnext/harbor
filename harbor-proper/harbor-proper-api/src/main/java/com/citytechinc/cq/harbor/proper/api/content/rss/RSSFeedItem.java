package com.citytechinc.cq.harbor.proper.api.content.rss;

public interface RSSFeedItem {

    public String getTitle();

    public void setTitle(String title);

    public String getLink();

    public void setLink(String link);

    public String getPubDate();

    public String getDescription();

    public String getHTML();

}
