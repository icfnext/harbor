package com.icfolson.aem.harbor.core.components.content.container.v1;

import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.harbor.api.components.design.container.ContainerDesign;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.InheritedTagBasedClassification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,adapters = Container.class, resourceType = DefaultContainer.RESOURCE_TYPE)
public class DefaultContainer extends AbstractComponent implements Container, ContainerDesign {

    public static final String RESOURCE_TYPE = "harbor/components/content/contentcontainer/v1/contentcontainer";

    private Classification classification;
    private ContainerDesign containerDesign;

    @Override
    public boolean isFullWidth() {
        return false;
    }

    @Override
    public Classification getClassification() {
        if (classification == null) {
            if (isInherits()) {
                classification = getResource().adaptTo(InheritedTagBasedClassification.class);
            } else {
                classification = getResource().adaptTo(TagBasedClassification.class);
            }
        }
        return classification;
    }

    public String getContainerElement() {
        return Elements.DIV;
    }

    @Override
    public boolean isHasRole() {
        return StringUtils.isNotBlank(getRole());
    }

    @Override
    public String getRole() {
        return null;
    }

    /**
     * Indicates whether instances of this container constitute a Section of content on a page.
     * Defaults to true.
     *
     * @return true if the container constitutes a Section of content on a page.
     */
    public boolean isSection() {
        return true;
    }

    @Override
    public boolean isInherits() {
        return false;
    }

    @Override
    public boolean isUsesResponsiveGrid() {
        return this.getContainerDesign().isUsesResponsiveGrid();
    }

    public ContainerDesign getContainerDesign() {
        if (this.containerDesign == null) {
            this.containerDesign = this.getResource().adaptTo(ContainerDesign.class);
        }

        return this.containerDesign;
    }

    @Override
    public String getParagraphSystemType() {
        return getContainerDesign().isUsesResponsiveGrid() ?
                ParagraphSystemContainer.RESPONSIVE_GRID : isInherits() ?
                ParagraphSystemContainer.I_PARSYS : ParagraphSystemContainer.PARSYS;
    }
}
