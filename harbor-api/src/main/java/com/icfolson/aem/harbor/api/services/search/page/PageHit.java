package com.icfolson.aem.harbor.api.services.search.page;

import javax.jcr.Node;

public interface PageHit {

    String getPagePath();

    Node getPageNode();

    String getExcerpt();

}
