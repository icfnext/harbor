package com.citytechinc.aem.harbor.core.components.content.pageheader;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;

@Component(
        value = "Bakedin Page Header",
        group = ".hidden",
        tabs = {
                @Tab(title = "Page Header"),
                @Tab(title = "Advanced")
        },
        resourceSuperType = PageHeader.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Page Header", "-", "edit" })
public class BakedinPageHeader extends PageHeader {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinpageheader";

}
