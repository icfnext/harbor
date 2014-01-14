package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

public class DefaultHomePage extends AbstractHierarchicalPage implements HomePage {

    public DefaultHomePage(PageDecorator page) {
        super(page);
    }

    @Override
    public List<SectionLandingPage> getSectionLandingPages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
