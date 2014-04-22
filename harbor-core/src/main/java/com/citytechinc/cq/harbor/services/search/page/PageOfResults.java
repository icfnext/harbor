package com.citytechinc.cq.harbor.services.search.page;

import java.util.List;

public class PageOfResults {

    private final int pageNbr;
    private final int totalNbrOfPages;
    private final List<PageHit> pageOfHits;

    public PageOfResults(int pageNbr, int totalNbrOfPages, List<PageHit> pageOfHits) {
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
