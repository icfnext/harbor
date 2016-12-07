package com.icfolson.aem.harbor.api.content.page;

import com.citytechinc.aem.namespace.api.ontology.Types;
import com.icfolson.aem.library.api.page.PageDecorator;

import java.util.List;

public interface HomePage extends HierarchicalPage, PageDecorator {

    String RDF_TYPE = Types.CITYTECH_HOME_PAGE;

    List<SectionLandingPage> getSectionLandingPages();
}
