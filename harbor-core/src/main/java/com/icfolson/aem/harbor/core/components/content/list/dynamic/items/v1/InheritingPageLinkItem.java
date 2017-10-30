package com.icfolson.aem.harbor.core.components.content.list.dynamic.items.v1;

import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.LinkItem;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.items.PageLinkItem;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.PageManagerDecorator;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {PageLinkItem.class, LinkItem.class, DynamicListItem.class}, resourceType = InheritingPageLinkItem.RESOURCE_TYPE)
public class InheritingPageLinkItem extends DefaultPageLinkItem {

    public static final String RESOURCE_TYPE = DefaultPageLinkItem.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Optional
    private String linkedPage;

    private PageDecorator linkedPageDecorator;

    @InheritInject @Optional
    private String label;

    @Inject @Self
    private Resource resource;

    @Override
    public PageDecorator getLinkedPage() {
        if (linkedPageDecorator == null && StringUtils.isNotBlank(linkedPage)) {
            linkedPageDecorator = resource.getResourceResolver().adaptTo(PageManagerDecorator.class).getPage(linkedPage);
        }

        return linkedPageDecorator;
    }

    @Override
    public String getAuthoredLabel() {
        return label;
    }

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

}
