package com.citytechinc.cq.harbor.proper.core.content.rss.impl;

import com.citytechinc.cq.harbor.proper.api.content.rss.RSSChannel;
import com.citytechinc.cq.harbor.proper.api.content.rss.RSSFeed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")
public class DefaultRSSFeed implements RSSFeed {

    @XmlElement(name = "channel")
    private DefaultRSSChannel rssChannel;

    @Override
    public RSSChannel getChannel() {
        return rssChannel;
    }

    public void setChannel(DefaultRSSChannel rssChannel) {
        this.rssChannel = rssChannel;
    }
}
