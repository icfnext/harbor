package com.icfolson.aem.harbor.core.content.page.impl

import com.icfolson.aem.harbor.api.content.page.HomePage
import com.icfolson.aem.library.api.page.PageDecorator

class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

    DefaultHomePage(PageDecorator page) {
        super(page)
    }

    @Override
    boolean isSubSectionLandingPage() {
        false
    }
}
