package com.icfolson.aem.harbor.api.content.page;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.namespace.api.ontology.Types;

import java.util.List;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage, PageDecorator {

    String RDF_TYPE = Types.ICF_OLSON_SECTION_LANDING_PAGE;

    boolean isSubSectionLandingPage();

    List<SectionLandingPage> getSectionLandingPages();

}
