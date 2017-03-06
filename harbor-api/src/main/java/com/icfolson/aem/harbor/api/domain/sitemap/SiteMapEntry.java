package com.icfolson.aem.harbor.api.domain.sitemap;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "loc", "lastmod", "changefreq", "priority" })
public class SiteMapEntry {

    protected String loc;

    protected String lastmod;

    protected String changefreq;

    protected String priority;

    public SiteMapEntry() {

    }

    public SiteMapEntry(final String loc, final String lastmod, final String changefreq, final String priority) {
        this.loc = loc;
        this.lastmod = lastmod;
        this.changefreq = changefreq;
        this.priority = priority;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("loc", getLoc())
            .add("lastmod", getLastmod())
            .add("changefreq", getChangefreq())
            .add("priority", getPriority())
            .toString();
    }
}
