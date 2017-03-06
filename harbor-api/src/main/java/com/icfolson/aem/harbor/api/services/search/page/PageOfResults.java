package com.icfolson.aem.harbor.api.services.search.page;

import java.util.List;

public interface PageOfResults {

    int getPageNbr();

    int getTotalNbrOfPages();

    List<PageHit> getPageOfHits();
}
