package com.icfolson.aem.harbor.core.components.content.accordion.v1;

import com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {AccordionItem.class, ParagraphSystemContainer.class}, resourceType = InheritingAccordionItem.RESOURCE_TYPE)
public class InheritingAccordionItem extends DefaultAccordionItem {

    public static final String RESOURCE_TYPE = DefaultAccordionItem.RESOURCE_TYPE + "/inheriting";

    @Inject @Self
    private Resource resource;

    @InheritInject @Optional
    private String title;

    public String getTitle() {
        if (StringUtils.isNotBlank(title)) {
            return title;
        }

        return getDefaultTitle();
    }

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }

}
