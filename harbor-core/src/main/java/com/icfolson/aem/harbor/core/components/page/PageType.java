package com.icfolson.aem.harbor.core.components.page;

import com.icfolson.aem.harbor.core.content.page.impl.PagePredicates;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class PageType {

    @Inject
    private PageDecorator currentPage;

    public boolean isHomePage() {
        return PagePredicates.HOME_PAGE_PREDICATE.apply(currentPage);
    }

    public boolean isSectionLandingPage() {
        return PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(currentPage);
    }
}
