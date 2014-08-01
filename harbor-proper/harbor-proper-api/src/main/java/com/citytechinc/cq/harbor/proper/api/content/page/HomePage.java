package com.citytechinc.cq.harbor.proper.api.content.page;

import java.util.List;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.cq.accelerate.api.ontology.Types;

public interface HomePage extends HierarchicalPage, PageDecorator {

	public static final String RDF_TYPE = Types.ACCELERATE_HOME_PAGE;

	public List<SectionLandingPage> getSectionLandingPages();

}
