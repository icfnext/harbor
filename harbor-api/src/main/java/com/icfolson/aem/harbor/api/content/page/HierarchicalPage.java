package com.icfolson.aem.harbor.api.content.page;

import com.google.common.base.Optional;

public interface HierarchicalPage {

    Optional<SectionLandingPage> getSectionLandingPage();

    Optional<HomePage> getHomePage();

    // TODO: on backlog public String getPageIconImage();

    String getPageIcon();

    boolean isStructuralPage();
}
