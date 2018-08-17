package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.TextItem;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {TextItem.class, DynamicListItem.class}, resourceType = InheritingTextItem.RESOURCE_TYPE)
public class InheritingTextItem extends DefaultTextItem {

    public static final String RESOURCE_TYPE = DefaultTextItem.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String text;

    @Inject
    private Resource resource;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getComponentNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
