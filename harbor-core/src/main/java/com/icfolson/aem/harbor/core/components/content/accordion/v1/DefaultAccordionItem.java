package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.accordion.Accordion;
import com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {AccordionItem.class, ParagraphSystemContainer.class}, resourceType = DefaultAccordionItem.RESOURCE_TYPE)
public class DefaultAccordionItem implements AccordionItem, ParagraphSystemContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/accordion/v1/accordionitem";

    private Integer index;
    private Accordion accordion;

    @Inject @Self
    private Resource resource;

    @Inject @Optional
    private String title;

    @DialogField(fieldLabel = "Title", fieldDescription = "The title of the accordion item.")
    @TextField
    public String getTitle() {
        if (StringUtils.isNotBlank(title)) {
            return title;
        }

        return getDefaultTitle();
    }

    @Override
    public Accordion getAccordion() {
        if (accordion == null) {
            accordion = getResource().getParent().adaptTo(Accordion.class);
        }

        return accordion;
    }

    @Override
    public int getItemIndex() {
        if (index == null) {
            index = getResource().adaptTo(ComponentNode.class).getIndex();
        }

        return index;
    }

    @Override
    public String getPath() {
        return getResource().getPath();
    }

    @Override
    public String getName() {
        return getResource().getName();
    }

    @Override
    public String getType() {
        return getResource().getResourceType();
    }

    @Override
    public boolean isExpanded() {
        return getItemIndex() == 0 && getAccordion().isOpenFirstItem();
    }

    protected String getDefaultTitle() {
        return "Accordion Item " + this.getItemIndex();
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String getId() {
        return getResource().adaptTo(ComponentNode.class).getId();
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.PARSYS;
    }

}