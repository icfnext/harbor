package com.icfolson.aem.harbor.core.content.page.v1

import com.icfolson.aem.harbor.api.content.page.HomePage

class DefaultHomePage extends DefaultSectionLandingPage implements HomePage {

    @Override
    boolean isSubSectionLandingPage() {
        false
    }

    @Override
    boolean isHomePage() {
        return true
    }

    @Override
    boolean isSectionLandingPage() {
        return true
    }
}
