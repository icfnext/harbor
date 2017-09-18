package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ResourceBasedListableLink;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = {ListableLink.class, ResourceBasedListableLink.class}, resourceType = DefaultListableLink.RESOURCE_TYPE)
public class DefaultListableLink extends AbstractComponent implements ResourceBasedListableLink {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/linklist/v1/listablelink";

    @DialogField(fieldLabel = "Link", fieldDescription = "Either an internal page path or a full external URL", ranking = 10) @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getHref() {
        return getAsHref("href").or("#");
    }

    @DialogField(fieldLabel = "Title", ranking = 0) @TextField
    public String getTitle() {
        return get("title", "Listable Link Item");
    }

    @DialogField(fieldLabel = "Open in new window", ranking = 20) @Switch
    public boolean isOpenInNewWindow() {
        return get("openInNewWindow", false);
    }

    @Override
    public String getName() {
        return getResource().getName();
    }

    @Override
    public String getType() {
        return getResource().getResourceType();
    }

}
