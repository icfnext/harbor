package com.citytechinc.cq.harbor.content.page.impl;

import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

    public DefaultHomePage(PageDecorator page) {
        super(page);
    }

    @Override
    public boolean isSubSectionLandingPage() {
        return false;
    }
}
