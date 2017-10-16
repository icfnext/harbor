package com.icfolson.aem.harbor.api.domain.sitemap;

public interface SiteMapEntry {

    String getLoc();

    void setLoc(String loc);

    String getLastmod();

    void setLastmod(String lastmod);

    String getChangefreq();

    void setChangefreq(String changefreq);

    String getPriority();

    void setPriority(String priority);

}
