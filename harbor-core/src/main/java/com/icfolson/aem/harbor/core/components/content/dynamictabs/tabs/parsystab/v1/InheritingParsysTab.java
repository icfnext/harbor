package com.icfolson.aem.harbor.core.components.content.dynamictabs.tabs.parsystab.v1;

import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables = Resource.class, adapters = {DynamicTab.class, ParagraphSystemContainer.class}, resourceType = InheritingParsysTab.RESOURCE_TYPE)
public class InheritingParsysTab extends ParsysTab {

    public static final String RESOURCE_TYPE = ParsysTab.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String label;

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

}
