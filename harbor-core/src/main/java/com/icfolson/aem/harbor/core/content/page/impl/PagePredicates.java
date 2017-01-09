package com.icfolson.aem.harbor.core.content.page.impl;

import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.content.page.HomePage;
import com.icfolson.aem.harbor.api.content.page.SectionLandingPage;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.HashMap;
import java.util.Map;

public class PagePredicates {

    private static final Logger LOG = LoggerFactory.getLogger(PagePredicates.class);

    public static final Predicate<PageDecorator> SECTION_LANDING_PAGE_PREDICATE = page -> {
        try {
            Node pageContentNode = page.getContentResource().adaptTo(Node.class);
            return pageContentNode.isNodeType(SectionLandingPage.RDF_TYPE);
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate", e);
            return false;
        }
    };

    public static final Predicate<PageDecorator> CONTENT_PAGE_PREDICATE = page -> {
        try {
            return page.getContentResource().adaptTo(Node.class).isNodeType("cq:PageContent");
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered while evaluating Hierarchical Page page predicate", e);
            return false;
        }
    };

    public static final Predicate<PageDecorator> HOME_PAGE_PREDICATE = page -> {
        try {
            Node pageContentNode = page.getContentResource().adaptTo(Node.class);
            return pageContentNode.isNodeType(HomePage.RDF_TYPE);
        } catch (RepositoryException e) {
            LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate", e);
            return false;
        }
    };

    public static final Predicate<PageDecorator> INCLUDE_ALL_CHILD_PAGE_TYPES = page -> PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(page)
        || PagePredicates.CONTENT_PAGE_PREDICATE.apply(page);

    public static final Map<String, Predicate<PageDecorator>> PREDICATE_MAP;

    static {
        PREDICATE_MAP = new HashMap<>();
        PREDICATE_MAP.put("HOME_PAGE_PREDICATE", HOME_PAGE_PREDICATE);
        PREDICATE_MAP.put("CONTENT_PAGE_PREDICATE", CONTENT_PAGE_PREDICATE);
        PREDICATE_MAP.put("SECTION_LANDING_PAGE_PREDICATE", SECTION_LANDING_PAGE_PREDICATE);
        PREDICATE_MAP.put("INCLUDE_ALL_CHILD_PAGE_TYPES", INCLUDE_ALL_CHILD_PAGE_TYPES);
    }

}
