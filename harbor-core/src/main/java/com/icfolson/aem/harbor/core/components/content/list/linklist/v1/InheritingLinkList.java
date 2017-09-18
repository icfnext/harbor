package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.icfolson.aem.harbor.api.components.content.list.linklist.LinkList;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = LinkList.class, resourceType = InheritingLinkList.RESOURCE_TYPE)
public class InheritingLinkList extends DefaultLinkList {

    public static final String RESOURCE_TYPE = DefaultLinkList.RESOURCE_TYPE + "/inheriting";

    public Resource getResource() {
        return super.getResource()
                .adaptTo(ComponentNode.class)
                .getNodeInherited(".")
                .transform(BasicNode::getResource)
                .orNull();
    }

}
