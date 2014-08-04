package com.citytechinc.cq.harbor.proper.api.content.page;

import java.util.List;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.accelerate.api.ontology.Types;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage, PageDecorator {

	public static final String RDF_TYPE = Types.ACCELERATE_SECTION_LANDING_PAGE;

	public boolean isSubSectionLandingPage();

	public List<SectionLandingPage> getSectionLandingPages();

}
