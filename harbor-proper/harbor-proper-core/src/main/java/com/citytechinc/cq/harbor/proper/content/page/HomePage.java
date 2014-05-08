package com.citytechinc.cq.harbor.proper.content.page;

import com.citytechinc.cq.harbor.ns.ontology.Types;

import java.util.List;

public interface HomePage extends HierarchicalPage {

    public static final String RDF_TYPE = Types.HARBOR_HOME_PAGE;

    public List<SectionLandingPage> getSectionLandingPages();

}
