package com.icfolson.aem.harbor.core.content.page.v1;

import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public final class PagePredicates {

    private static final Logger LOG = LoggerFactory.getLogger(PagePredicates.class);

    public static final Predicate<PageDecorator> SECTION_LANDING_PAGE_PREDICATE = page ->
            page != null && page.getContentResource() != null &&
                    page.getContentResource().adaptTo(HierarchicalPage.class).isSectionLandingPage();

    public static final Predicate<PageDecorator> HOME_PAGE_PREDICATE = page ->
            page != null && page.getContentResource() != null &&
                    page.getContentResource().adaptTo(HierarchicalPage.class).isHomePage();

    public static final Predicate<PageDecorator> ALL_PAGES_PREDICATE = page -> true;

    public static final Predicate<PageDecorator> NAVIGABLE_PAGES_PREDICATE = page ->
            (page != null && !page.isHideInNav());

    private PagePredicates() {

    }
}
