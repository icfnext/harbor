package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTabs;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = DynamicTabs.class, resourceType = DefaultDynamicTabs.RESOURCE_TYPE)
public class DefaultDynamicTabs implements DynamicTabs {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamictabs/v1/dynamictabs";

    @Inject
    private Resource resource;

    public List<DynamicTab> getTabs() {
        //TODO: I feel like I should not have to do this - the injection of the list should be able to adapt to the tabs directly.  Check into http://svn.apache.org/repos/asf/sling/trunk/bundles/extensions/models/impl/src/main/java/org/apache/sling/models/impl/ModelAdapterFactory.java
        //Caused by: org.apache.sling.models.factory.ModelClassException: interface java.util.List is neither a parameterized Collection or List
        return Lists.newArrayList(resource.getChildren())
                .stream()
                .map(r -> r.adaptTo(DynamicTab.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }

}
