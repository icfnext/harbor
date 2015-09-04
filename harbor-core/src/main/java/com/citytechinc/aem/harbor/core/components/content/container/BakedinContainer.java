package com.citytechinc.aem.harbor.core.components.content.container;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;

@Component(
        value = "Bakedin Container",
        name = "bakedincontentcontainer",
        group = ".hidden",
        tabs = {
                @Tab(title = "Container"),
                @Tab(title = "Advanced")
        },
        resourceSuperType = Container.RESOURCE_TYPE,
        disableTargeting = true,
        actions = { "text: Content Container", "-", "edit" })
@AutoInstantiate(instanceName = Container.INSTANCE_NAME)
public class BakedinContainer extends Container {

    public static final String RESOURCE_TYPE = "harbor/components/content/bakedincontentcontainer";

    @Override
    protected boolean isInherits() {
        return true;
    }

}
