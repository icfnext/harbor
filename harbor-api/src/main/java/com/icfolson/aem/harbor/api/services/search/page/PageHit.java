package com.icfolson.aem.harbor.api.services.search.page;

import javax.jcr.Node;

public interface PageHit {

	public String getPagePath();

	public Node getPageNode();

	public String getExcerpt();

}
