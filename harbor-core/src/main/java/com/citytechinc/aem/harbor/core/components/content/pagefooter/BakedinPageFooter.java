package com.citytechinc.aem.harbor.core.components.content.pagefooter;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;

@Component(
        value = "Bakedin Page Footer",
        group = ".hidden",
        tabs = {
                @Tab(title = "Page Footer"),
                @Tab(title = "Advanced")
        },
        resourceSuperType = PageFooter.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Page Footer", "-", "edit" })
public class BakedinPageFooter extends PageFooter {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedinpagefooter";

}
