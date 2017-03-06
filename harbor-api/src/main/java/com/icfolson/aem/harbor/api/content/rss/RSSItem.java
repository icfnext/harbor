package com.icfolson.aem.harbor.api.content.rss;

public interface RSSItem {

    String getTitle();

    String getLink();

    String getDescription();

    String getAuthor();

    // TODO: Categories

    String getComments();

    // TODO: enclosure
    // TODO: guid

    String getPubDate();

    String getSource();

}
