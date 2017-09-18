package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ResourceBasedListableLink;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = {ListableLink.class, ResourceBasedListableLink.class}, resourceType = InheritingListableLink.RESOURCE_TYPE)
public class InheritingListableLink extends DefaultListableLink {

    public static final String RESOURCE_TYPE = DefaultListableLink.RESOURCE_TYPE + "/inheriting";

    public String getHref() {
        return getAsHrefInherited("href").or("#");
    }

    public String getTitle() {
        return getInherited("title", "Listable Link Item");
    }

    public boolean isOpenInNewWindow() {
        return getInherited("openInNewWindow", false);
    }

}
