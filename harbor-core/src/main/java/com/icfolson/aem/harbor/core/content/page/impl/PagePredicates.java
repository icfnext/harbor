package com.icfolson.aem.harbor.core.content.page.impl;

import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public final class PagePredicates {

    private static final Logger LOG = LoggerFactory.getLogger(PagePredicates.class);

    public static final Predicate<PageDecorator> SECTION_LANDING_PAGE_PREDICATE = page -> {
        try {
            return page.getContentResource().adaptTo(Node.class).isNodeType(SectionLandingPage.RDF_TYPE);
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate", e);
            return false;
        }
    };

    public static final Predicate<PageDecorator> HOME_PAGE_PREDICATE = page -> {
        try {
            return page.getContentResource().adaptTo(Node.class).isNodeType(HomePage.RDF_TYPE);
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate", e);
            return false;
        }
    };

    private PagePredicates() {

    }
}
