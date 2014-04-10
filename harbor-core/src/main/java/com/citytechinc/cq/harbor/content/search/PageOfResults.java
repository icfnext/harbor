package com.citytechinc.cq.harbor.content.search;

import java.util.List;

public class PageOfResults {

    private final int pageNbr;
    private final int totalNbrOfPages;
    private final List<ContentHit> pageOfHits;

    public PageOfResults(int pageNbr, int totalNbrOfPages, List<ContentHit> pageOfHits) {
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

    public List<ContentHit> getPageOfHits() {
        return pageOfHits;
    }

}
