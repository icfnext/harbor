package com.icfolson.aem.harbor.core.components.page.global.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.components.page.global.Global;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(value = "Harbor Global Page", editConfig = false, path = "/page/common", name = "global/v1/global", tabs = {
    @Tab(touchUIPath = DefaultGlobalPage.RESOURCE_TYPE + "/touch-sitemap/content/items/SitemappedPage"),
    @Tab(touchUIPath = DefaultGlobalPage.RESOURCE_TYPE + "/touch-metadata/content/items/MetaPage")
})
@Model(adaptables = Resource.class, adapters = Global.class, resourceType = DefaultGlobalPage.RESOURCE_TYPE)
public class DefaultGlobalPage implements Global {

    public static final String RESOURCE_TYPE = "harbor/components/page/common/global/v1/global";

}
