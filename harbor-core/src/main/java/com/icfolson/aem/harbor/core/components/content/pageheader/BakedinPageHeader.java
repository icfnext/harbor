package com.icfolson.aem.harbor.core.components.content.pageheader;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Bakedin Page Header",
    group = ".hidden",
        tabs = {
                @Tab(title = "Container"),
                @Tab(title = "Advanced")
        },
    resourceSuperType = PageHeader.RESOURCE_TYPE,
    disableTargeting = true,
    actions = { "text: Page Header", "-", "edit" } )
@Model(adaptables = Resource.class)
public class BakedinPageHeader extends PageHeader {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinpageheader";

}
