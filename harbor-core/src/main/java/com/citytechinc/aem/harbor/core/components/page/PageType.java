package com.citytechinc.aem.harbor.core.components.page;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.content.page.impl.PagePredicates;

public class PageType extends AbstractComponent {

    public boolean isHomePage() {
        return PagePredicates.HOME_PAGE_PREDICATE.apply(getCurrentPage());
    }

    public boolean isSectionLandingPage() {
        return PagePredicates.SECTION_LANDING_PAGE_PREDICATE.apply(getCurrentPage());
    }

}
