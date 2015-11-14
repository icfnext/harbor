package com.citytechinc.aem.harbor.core.components.page.global;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;

@Component(value = "Harbor Global Page", editConfig = false, path = "/page/common", tabs = {
        /*
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/basic"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/advanced"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/thumbnail"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/cloudservices"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/permissions"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/blueprint"),
        @Tab(touchUIPath = "foundation/components/page/cq:dialog/content/items/tabs/items/livecopy"),
        */
        @Tab(touchUIPath = "harbor/components/page/common/global/touch-sitemap/content/items/SitemappedPage"),
        @Tab(touchUIPath = "harbor/components/page/common/global/touch-metadata/content/items/MetaPage"),
        @Tab(touchUIPath = "harbor/components/page/common/global/touch-theme/content/items/ThemedPage")
})
public class Global {
}
