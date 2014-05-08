package com.citytechinc.cq.harbor.proper.content.page;

import com.citytechinc.cq.harbor.ns.ontology.Types;

import java.util.List;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage {

    public static final String RDF_TYPE = Types.HARBOR_SECTION_LANDING_PAGE;

    public boolean isSubSectionLandingPage();

    public List<SectionLandingPage> getSectionLandingPages();
}
