package com.icfolson.aem.harbor.core.components.content.list.linklist.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.content.list.linklist.LinkList;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ResourceBasedListableLink;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = LinkList.class, resourceType = DefaultLinkList.RESOURCE_TYPE)
public class DefaultLinkList implements LinkList<ResourceBasedListableLink> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/linklist/v1/linklist";

    @Inject @Self
    private Resource resource;

    List<ResourceBasedListableLink> items;

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public Iterable<ResourceBasedListableLink> getItems() {
        if (items == null) {
            items = Lists.newArrayList(getResource().getChildren())
                    .stream()
                    .map(currentResource -> currentResource.adaptTo(ResourceBasedListableLink.class))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return items;
    }

    @Override
    public String getListType() {
        return ListComponent.UNORDERED_LIST_TYPE;
    }

    public Resource getResource() {
        return resource;
    }

}
