package com.icfolson.aem.harbor.api.content.page;

import com.citytechinc.aem.namespace.api.ontology.Types;
import com.google.common.base.Optional;

public interface HierarchicalPage {

    String RDF_TYPE = Types.CITYTECH_HIERARCHICAL_PAGE;

    Optional<SectionLandingPage> getSectionLandingPage();

    Optional<HomePage> getHomePage();

    // TODO: on backlog public String getPageIconImage();

    String getPageIcon();

    boolean isStructuralPage();
}
