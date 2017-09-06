package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.TextItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {TextItem.class, DynamicListItem.class}, resourceType = DefaultTextItem.RESOURCE_TYPE)
public class DefaultTextItem implements TextItem {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist/items/textitem/v1/textitem";

    @Inject @Optional
    private String text;

    @Inject
    private Resource resource;

    @DialogField(fieldLabel = "Text Content") @TextField
    public String getText() {
        return this.text;
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
