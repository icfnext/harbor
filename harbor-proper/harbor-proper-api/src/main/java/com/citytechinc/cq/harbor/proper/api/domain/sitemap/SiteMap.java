package com.citytechinc.cq.harbor.proper.api.domain.sitemap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class SiteMap {

    @XmlElement(name="url")
    final List<SiteMapEntry> siteMapEntries;

    public SiteMap() {
        siteMapEntries = newArrayList();
    }

    public SiteMap(List<SiteMapEntry> siteMapEntries) {
        this.siteMapEntries = siteMapEntries;
    }

    public List<SiteMapEntry> getSiteMapEntries() {
        return siteMapEntries;
    }
}
