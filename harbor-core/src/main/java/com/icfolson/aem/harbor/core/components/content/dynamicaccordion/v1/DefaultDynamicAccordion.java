package com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem;
import com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordion;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@Model(adaptables = Resource.class, adapters = DynamicAccordion.class, resourceType = DefaultDynamicAccordion.RESOURCE_TYPE)
public class DefaultDynamicAccordion implements DynamicAccordion<DynamicAccordionItem> {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamicaccordion/v1/dynamicaccordion";

    @Inject
    private Resource resource;

    public List<DynamicAccordionItem> getItems() {
        return Lists.newArrayList(getResource().getChildren())
                .stream()
                .map(r -> r.adaptTo(DynamicAccordionItem.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(getResource().getPath());
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

    public Resource getResource() {
        return resource;
    }

}
