package com.icfolson.aem.harbor.core.components.page.global.v1;

import com.icfolson.aem.harbor.api.components.page.global.Global;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = Global.class, resourceType = DefaultGlobalPage.RESOURCE_TYPE)
public class DefaultGlobalPage implements Global {

    public static final String RESOURCE_TYPE = "harbor/components/page/common/global/v1/global";

}
