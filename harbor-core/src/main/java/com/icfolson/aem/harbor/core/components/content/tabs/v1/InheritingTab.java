package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.icfolson.aem.harbor.api.components.content.tabs.Tab;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {Tab.class, ParagraphSystemContainer.class}, resourceType = InheritingTab.RESOURCE_TYPE)
public class InheritingTab extends DefaultTab {

    public static final String RESOURCE_TYPE = DefaultTab.RESOURCE_TYPE + "/inheriting";

    @InheritInject @Default(values = "Tab Label")
    private String label;

    @Inject @Self
    private Resource resource;

    @Override
    public String getLabel() {
        return label;
    }

    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }

}
