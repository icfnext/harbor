package com.icfolson.aem.harbor.core.components.page.global.v1;

import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = ParagraphSystemContainer.class, resourceType = DefaultGlobalPage.RESOURCE_TYPE)
public class PageParagraphSystemContainer implements ParagraphSystemContainer {

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.RESPONSIVE_GRID;
    }

}
