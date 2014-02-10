package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.constants.ontology.Properties;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public class PagePredicates {

    private static final Logger LOG = LoggerFactory.getLogger(PagePredicates.class);

    public static final Predicate<PageDecorator> SECTION_LANDING_PAGE_PREDICATE = new Predicate<PageDecorator>() {

        @Override
        public boolean apply(final PageDecorator page) {
            try {
                return page.getContentResource().adaptTo(Node.class).isNodeType(SectionLandingPage.RDF_TYPE);
            } catch (RepositoryException e) {
                LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate");
                return false;
            }
        }

    };

    public static final Predicate<PageDecorator> HIERARCHICAL_PAGE_PREDICATE = new Predicate<PageDecorator>() {

        @Override
        public boolean apply(final PageDecorator page) {
            try {
                return page.getContentResource().adaptTo(Node.class).isNodeType(HierarchicalPage.RDF_TYPE);
            } catch (RepositoryException e) {
                LOG.error("Repository Exception encountered while evaluating Hierarchical Page page predicate");
                return false;
            }
        }

    };

    public static final Predicate<PageDecorator> HOME_PAGE_PREDICATE = new Predicate<PageDecorator>() {

        @Override
        public boolean apply(final PageDecorator page) {
            try {
                return page.getContentResource().adaptTo(Node.class).isNodeType(HomePage.RDF_TYPE);
            } catch (RepositoryException e) {
                LOG.error("Repository Exception encountered while evaluating Section Landing Page page predicate");
                return false;
            }
        }

    };

}
