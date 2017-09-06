package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.tabs.Tab;
import com.icfolson.aem.harbor.api.components.content.tabs.Tabs;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, adapters = Tabs.class, resourceType = DefaultTabs.RESOURCE_TYPE)
public class DefaultTabs implements Tabs {

    public static final String RESOURCE_TYPE = "harbor/components/content/tabs/v1/tabs";

    @Inject @Self
    private Resource resource;

    private List<Tab> tabs;

    public List<Tab> getTabs() {
        if (tabs == null) {
            tabs = Lists.newArrayList();
            resource.getChildren().forEach(tabResource -> {
                Tab currentTab = tabResource.adaptTo(Tab.class);
                if (currentTab != null) {
                    tabs.add(currentTab);
                }
            });
        }

        return tabs;
    }

}