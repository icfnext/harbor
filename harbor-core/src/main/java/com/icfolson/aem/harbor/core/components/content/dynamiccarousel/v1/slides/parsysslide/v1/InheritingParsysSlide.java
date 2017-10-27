package com.icfolson.aem.harbor.core.components.content.dynamiccarousel.v1.slides.parsysslide.v1;

import com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.library.api.node.BasicNode;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {DynamicCarouselSlide.class, ParagraphSystemContainer.class}, resourceType = InheritingParsysSlide.RESOURCE_TYPE)
public class InheritingParsysSlide extends ParsysSlide {

    public static final String RESOURCE_TYPE = ParsysSlide.RESOURCE_TYPE + "/inheriting";

    @Inject
    private Resource resource;

    @Override
    public Resource getResource() {
        return resource.adaptTo(ComponentNode.class).getNodeInherited(".").transform(BasicNode::getResource).or(super.getResource());
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.I_PARSYS;
    }

}
