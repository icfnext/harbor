package com.citytechinc.aem.harbor.core.content.rss.impl;

import com.citytechinc.aem.harbor.api.content.rss.RSSChannel;
import com.citytechinc.aem.harbor.api.content.rss.RSSItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "channel")
public class DefaultRSSChannel implements RSSChannel {

    @XmlElement
    private String title;
    @XmlElement
    private String link;
    @XmlElement
    private String description;
    @XmlElement(name = "item")
    private List<DefaultRSSItem> rssItems;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<? extends RSSItem> getItems() {
        return rssItems;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRssItems(List<DefaultRSSItem> rssItems) {
        this.rssItems = rssItems;
    }
}
