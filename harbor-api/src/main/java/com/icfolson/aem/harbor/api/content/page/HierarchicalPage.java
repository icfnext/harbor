package com.icfolson.aem.harbor.api.content.page;

import com.google.common.base.Optional;

public interface HierarchicalPage {

    Optional<SectionLandingPage> getSectionLandingPage();

    Optional<HomePage> getHomePage();

    // TODO: on backlog public String getPageIconImage();

    String getPageIcon();

    boolean isStructuralPage();

    /**
     * Indicates whether the current hierarchical page is a Home Page.
     * @return
     */
    boolean isHomePage();

    /**
     * Indicates whether the current hierarchical page is a Section Landing Page.
     *
     * This method is needed due to the existence of `org.apache.sling.models.impl.FirstImplementationPicker`.
     * Because of that implementation, we can not rely on resource type constrained adaptation to determine whether
     * a resource adapts appropriately to a given interface, because any resource can be adapted to any modeled
     * interface based on the FirstImplementationPicker.  So, this is something of a hack to let us specify at
     * an implementation level what type of implementation it is.
     *
     * @return Indication of whether the page is a Section Landing Page
     */
    boolean isSectionLandingPage();

}
