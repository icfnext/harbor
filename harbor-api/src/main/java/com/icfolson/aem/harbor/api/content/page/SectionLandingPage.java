package com.icfolson.aem.harbor.api.content.page;

import java.util.List;

import com.icfolson.aem.library.api.page.PageDecorator;
import com.citytechinc.aem.namespace.api.ontology.Types;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage, PageDecorator {

	public static final String RDF_TYPE = Types.CITYTECH_SECTION_LANDING_PAGE;

	public boolean isSubSectionLandingPage();

	public List<SectionLandingPage> getSectionLandingPages();

}
