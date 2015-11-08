package com.citytechinc.aem.harbor.core.components.page;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.core.content.page.impl.PagePredicates;
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
