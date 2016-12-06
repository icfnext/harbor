package com.icfolson.aem.harbor.api.content.page;

import com.citytechinc.aem.namespace.api.ontology.Types;
import com.icfolson.aem.library.api.page.PageDecorator;

import java.util.List;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage, PageDecorator {

    String RDF_TYPE = Types.CITYTECH_SECTION_LANDING_PAGE;

    boolean isSubSectionLandingPage();

    List<SectionLandingPage> getSectionLandingPages();

}
