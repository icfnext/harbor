package com.icfolson.aem.harbor.core.content.page.v1

import com.icfolson.aem.harbor.api.content.page.HierarchicalPage

class DefaultStructuralPage extends DefaultHierarchicalPage {

    @Override
    boolean isStructuralPage() {
        true
    }

}
