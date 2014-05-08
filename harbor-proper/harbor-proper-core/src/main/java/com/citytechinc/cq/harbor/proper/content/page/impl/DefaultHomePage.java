package com.citytechinc.cq.harbor.proper.content.page.impl;

import com.citytechinc.cq.harbor.proper.content.page.HomePage;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Optional;

public class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

    public DefaultHomePage(PageDecorator page) {
        super(page);
    }

    @Override
    public boolean isSubSectionLandingPage() {
        return false;
    }
}
