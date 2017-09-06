package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.icfolson.aem.harbor.api.components.content.accordion.Accordion;
import com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = Accordion.class, resourceType = DefaultAccordion.RESOURCE_TYPE)
public class DefaultAccordion extends AbstractComponent implements Accordion {

    public static final String RESOURCE_TYPE = "harbor/components/content/accordion/v1/accordion";

    private List<AccordionItem> accordionItems;

    public String getName() {
        return getResource().getName();
    }

    public boolean isOpenFirstItem() {
        return get("openFirstItem", false);
    }

    public List<AccordionItem> getItems() {
        if (accordionItems == null) {
            accordionItems = getComponentNodes()
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(AccordionItem.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }

        return accordionItems;
    }

    public boolean isHasItems() {
        return !getItems().isEmpty();
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

}