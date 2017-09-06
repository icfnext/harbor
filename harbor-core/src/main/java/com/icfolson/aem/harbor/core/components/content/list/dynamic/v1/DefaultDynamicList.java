package com.icfolson.aem.harbor.core.components.content.list.dynamic.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicList;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = DynamicList.class, resourceType = DefaultDynamicList.RESOURCE_TYPE)
public class DefaultDynamicList implements DynamicList<DynamicListItem> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/dynamiclist/v1/dynamiclist";

    @Inject
    private Resource resource;

    private List<DynamicListItem> items;

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

    @Override
    public Iterable<DynamicListItem> getItems() {
        if (items == null) {
            items = Lists.newArrayList(resource.getChildren())
                    .stream()
                    .map(currentChild -> currentChild.adaptTo(DynamicListItem.class))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return items;
    }

    @Override
    public String getListType() {
        return ListComponent.UNORDERED_LIST_TYPE;
    }

}
