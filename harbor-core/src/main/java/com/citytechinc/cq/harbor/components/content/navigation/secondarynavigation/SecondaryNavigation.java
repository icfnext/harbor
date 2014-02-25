package com.citytechinc.cq.harbor.components.content.navigation.secondarynavigation;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;


@Component(value = "Secondary Navigation",
        actions = {"text: Global Navigation", "-", "edit", "-", "delete"},
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys"
)
@AutoInstantiate( instanceName = "secondaryNavigation" )
public class SecondaryNavigation {
    public SecondaryNavigation(ComponentRequest request) {
        //super(request);
    }
}
