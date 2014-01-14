package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

import java.util.List;

public class DefaultSectionLandingPage extends DefaultHierarchicalPage implements SectionLandingPage {

    public DefaultSectionLandingPage(PageDecorator page) {
        super(page);
    }

    @Override
    public boolean isSubSectionLandingPage() {
        return getSectionLandingPage().isPresent();
    }

    @Override
    public List<SectionLandingPage> getSubSectionLandingPages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
