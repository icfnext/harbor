package com.citytechinc.cq.harbor.content.search;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ContentHit {

    private final Node node;
    private final String excerpt;

    public ContentHit(Node node, String excerpt) {
        this.node = node;
        this.excerpt = excerpt;
    }

    public String getPath() {
        try {
            return node.getPath();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
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
        ContentHit rhs = (ContentHit) obj;
        return new EqualsBuilder()
                .append(getPath(), rhs.getPath())
                .append(excerpt, rhs.excerpt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(getPath()).
                append(excerpt).
                toHashCode();
    }
}
