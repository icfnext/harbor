package com.citytechinc.cq.harbor.proper.core.services.search.page.impl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import com.citytechinc.cq.harbor.proper.api.services.search.page.PageHit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DefaultPageHit implements PageHit {

    private final Node pageNode;
    private final String excerpt;

    public DefaultPageHit(Node pageNode, String excerpt) {
        this.pageNode = pageNode;
        this.excerpt = excerpt;
    }

    public String getPagePath() {
        try {
            return pageNode.getPath();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Node getPageNode() {
        return pageNode;
    }

    public String getExcerpt() {
        return excerpt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        DefaultPageHit rhs = (DefaultPageHit) obj;
        return new EqualsBuilder()
                .append(getPagePath(), rhs.getPagePath())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(getPagePath()).
                toHashCode();
    }
}
