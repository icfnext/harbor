package com.icfolson.aem.harbor.core.content.page.lists.construction;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class InheritingPageTrailListConstructionStrategy extends PageTrailListConstructionStrategy {

    protected boolean isInherits() {
        return true;
    }

}
