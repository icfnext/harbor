package com.citytechinc.cq.harbor.content.page;

import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

public interface HomePage extends PageDecorator {

    public static final String RDF_TYPE = "harbor:HomePage";

    public List<SectionLandingPage> getSectionLandingPages();

}
