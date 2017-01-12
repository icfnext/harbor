package com.icfolson.aem.harbor.api.content.page;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.namespace.api.ontology.Types;

import java.util.List;

public interface HomePage extends HierarchicalPage, PageDecorator {

    String RDF_TYPE = Types.ICF_OLSON_HOME_PAGE;

    List<SectionLandingPage> getSectionLandingPages();
}
