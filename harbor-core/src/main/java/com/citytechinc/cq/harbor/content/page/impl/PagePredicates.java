package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.constants.ontology.Properties;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Predicate;
import org.apache.commons.lang.StringUtils;

public class PagePredicates {

    public static final Predicate<PageDecorator> SECTION_LANDING_PAGE_PREDICATE = new Predicate<PageDecorator>() {

        @Override
        public boolean apply(final PageDecorator page) {
            return page.getProperties().get(Properties.RDF_TYPE, StringUtils.EMPTY).equals(SectionLandingPage.RDF_TYPE);
        }

    };

    public static final Predicate<PageDecorator> HOME_PAGE_PREDICATE = new Predicate<PageDecorator>() {

        @Override
        public boolean apply(final PageDecorator page) {
            return page.getProperties().get(Properties.RDF_TYPE, StringUtils.EMPTY).equals(HomePage.RDF_TYPE);
        }

    };

}
