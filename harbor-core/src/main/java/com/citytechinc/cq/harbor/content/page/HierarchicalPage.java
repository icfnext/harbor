package com.citytechinc.cq.harbor.content.page;


import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

public interface HierarchicalPage extends PageDecorator {

    public static final String RDF_TYPE = "harbor:HierarchicalPage";

    public Optional<SectionLandingPage> getSectionLandingPage();

    public Optional<HomePage> getHomePage();

    // TODO: on backlog public String getPageIconImage();

    public String getPageIcon();

}
