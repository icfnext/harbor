package com.citytechinc.cq.harbor.proper.api.content.page;

import com.citytechinc.cq.accelerate.api.ontology.Types;

import java.util.List;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage {

    public static final String RDF_TYPE = Types.ACCELERATE_SECTION_LANDING_PAGE;

    public boolean isSubSectionLandingPage();

    public List<SectionLandingPage> getSectionLandingPages();

}
