package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.icfolson.aem.harbor.api.components.content.list.linklist.LinkList;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = LinkList.class, resourceType = InheritingLinkList.RESOURCE_TYPE)
public class InheritingLinkList extends DefaultLinkList {

    public static final String RESOURCE_TYPE = DefaultLinkList.RESOURCE_TYPE + "/inheriting";

    @Inject @Self
    private Resource resource;

    /**
     * Looks up the page tree to find a concrete resource at this instance's relative path.  If one
     * is found it is returned.  If one is not found then the faux Resource representing this instance
     * is returned.
     *
     * @return Concrete Resource in the content tree if one exists or the faux Resource otherwise
     */
    public Resource getResource() {
        return super.getResource()
                .adaptTo(ComponentNode.class)
                .getComponentNodeInherited(".")
                .transform(BasicNode::getResource)
                .or(resource);
    }

}
