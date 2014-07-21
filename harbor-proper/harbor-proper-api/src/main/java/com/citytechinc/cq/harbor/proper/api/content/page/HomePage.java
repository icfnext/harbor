package com.citytechinc.cq.harbor.proper.api.content.page;

import com.citytechinc.cq.accelerate.api.ontology.Types;

import java.util.List;

public interface HomePage extends HierarchicalPage {

    public static final String RDF_TYPE = Types.ACCELERATE_HOME_PAGE;

    public List<SectionLandingPage> getSectionLandingPages();

}
