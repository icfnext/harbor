package com.citytechinc.cq.harbor.proper.core.services.search.page.impl;

import com.citytechinc.cq.harbor.proper.api.services.search.page.PageHit;
import com.citytechinc.cq.harbor.proper.api.services.search.page.PageOfResults;

import java.util.List;

public class DefaultPageOfResults implements PageOfResults {

    private final int pageNbr;
    private final int totalNbrOfPages;
    private final List<PageHit> pageOfHits;

    public DefaultPageOfResults(int pageNbr, int totalNbrOfPages, List<PageHit> pageOfHits) {
        this.pageNbr = pageNbr;
        this.totalNbrOfPages = totalNbrOfPages;
        this.pageOfHits = pageOfHits;
    }

    public int getPageNbr() {
        return pageNbr;
    }

    public int getTotalNbrOfPages() {
        return totalNbrOfPages;
    }

    public List<PageHit> getPageOfHits() {
        return pageOfHits;
    }

}
