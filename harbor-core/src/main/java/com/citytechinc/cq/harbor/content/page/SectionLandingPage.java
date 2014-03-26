package com.citytechinc.cq.harbor.content.page;

import java.util.List;

/**
 *
 */
public interface SectionLandingPage extends HierarchicalPage {

    public static final String RDF_TYPE = "harbor:SectionLandingPage";

    public boolean isSubSectionLandingPage();

    public List<SectionLandingPage> getSectionLandingPages();
}
