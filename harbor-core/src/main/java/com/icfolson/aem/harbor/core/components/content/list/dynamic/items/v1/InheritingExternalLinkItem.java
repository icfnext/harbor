package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.ExternalLinkItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.LinkItem;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {ExternalLinkItem.class, LinkItem.class, DynamicListItem.class}, resourceType = InheritingExternalLinkItem.RESOURCE_TYPE)
public class InheritingExternalLinkItem extends DefaultExternalLinkItem {

    public static final String RESOURCE_TYPE = DefaultExternalLinkItem.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String url;

    @InheritInject @Optional
    private String label;

    @Inject @Self
    private Resource resource;

    public String getUrl() {
        return url;
    }

    public String getLabel() {
        return label;
    }

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
