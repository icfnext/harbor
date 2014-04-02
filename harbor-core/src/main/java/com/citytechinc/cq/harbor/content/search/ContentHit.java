package com.citytechinc.cq.harbor.content.search;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

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

}
