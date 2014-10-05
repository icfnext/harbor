package com.citytechinc.aem.harbor.api.services.search.page;

import java.util.List;

public interface PageOfResults {

	public int getPageNbr();

	public int getTotalNbrOfPages();

	public List<PageHit> getPageOfHits();
}
