package com.icfolson.aem.harbor.api.content.page;

import com.citytechinc.aem.namespace.api.ontology.Types;
import com.google.common.base.Optional;

public interface HierarchicalPage {

	public static final String RDF_TYPE = Types.CITYTECH_HIERARCHICAL_PAGE;

	public Optional<SectionLandingPage> getSectionLandingPage();

	public Optional<HomePage> getHomePage();

	// TODO: on backlog public String getPageIconImage();

	public String getPageIcon();

    public boolean isStructuralPage();

}
