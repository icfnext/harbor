package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.ExternalLinkItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.LinkItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {ExternalLinkItem.class, LinkItem.class, DynamicListItem.class}, resourceType = DefaultExternalLinkItem.RESOURCE_TYPE)
public class DefaultExternalLinkItem implements ExternalLinkItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist/items/externallinkitem/v1/externallinkitem";

    @Inject @Optional
    private String url;

    @Inject @Optional
    private String label;

    @Inject @Self
    private Resource resource;

    @DialogField(fieldLabel = "URL") @TextField
    public String getUrl() {
        return url;
    }

    @DialogField(fieldLabel = "Label") @TextField
    public String getLabel() {
        return label;
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

    @Override
    public String getType() {
        return resource.getResourceType();
    }
}
