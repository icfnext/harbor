package com.citytechinc.cq.harbor.proper.api.services.search.page;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public interface PageHit {

    public String getPagePath();
    
    public Node getPageNode();

    public String getExcerpt();

}
