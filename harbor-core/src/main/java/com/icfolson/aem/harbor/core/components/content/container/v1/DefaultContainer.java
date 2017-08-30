package com.icfolson.aem.harbor.core.components.content.container.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.harbor.api.components.design.container.ContainerDesign;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.InheritedClassification;
import com.icfolson.aem.harbor.api.components.mixins.inheritable.Inheritable;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;

@Component(
    value = "Container (v1)",
    description = "A container in which content may be placed.  All content should be placed in a container element.",
    name = "contentcontainer/v1/contentcontainer",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    isContainer = true,
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class,adapters = Container.class, resourceType = DefaultContainer.RESOURCE_TYPE)
public class DefaultContainer extends AbstractComponent implements Container, Inheritable, ContainerDesign {

    public static final String RESOURCE_TYPE = "harbor/components/content/contentcontainer/v1/contentcontainer";

    public static final String FULL_WIDTH_PROPERTY = "fullWidth";

    private Classification classification;
    private ContainerDesign containerDesign;

    @PostConstruct
    public void init() {
        if (isInherits()) {
            classification = getResource().adaptTo(InheritedClassification.class);
        } else {
            classification = getResource().adaptTo(Classification.class);
        }
    }

    @DialogField(fieldLabel = "Full Width",
        fieldDescription = "When set to true, the container will render across the full width of the browser window",
        name = "./" + FULL_WIDTH_PROPERTY, ranking = 0)
    @Switch(offText = "No", onText = "Yes")
    public boolean isContainerFullWidth() {
        return isInherits() ? getInherited(FULL_WIDTH_PROPERTY, false) : get(FULL_WIDTH_PROPERTY, false);
    }

    @DialogField(fieldLabel = "Container Inheritance",
        fieldDescription = "When enabled, an inheriting paragraph system is produced for this container instance.",
        ranking = 10)
    @Switch(offText = "No", onText = "Yes")
    public boolean isParsysInherits() {
        return isInherits() ? getInherited("parsysInherits", false) : get("parsysInherits", false);
    }

    @DialogField(ranking = 20)
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

    @DialogField(fieldLabel = "ID",
        fieldDescription = "A unique identifier to apply to the Container element rendered in the page DOM.  If left blank, no id attribute will be applied to the rendered element.",
        tab = 2)
    @TextField
    public String getDomId() {
        return isInherits() ? getInherited("domId", "") : get("domId", "");
    }

    public String getContainerClass() {
        final StringBuilder builder = new StringBuilder();

        if (isContainerFullWidth()) {
            builder.append(getContainerFullWidthClass());
        } else {
            builder.append(getContainerDefaultClass());
        }

        if (!isSection() && classification.isHasClassifications()) {
            builder.append(" ").append(StringUtils.join(classification.getClassificationNames(), " "));
        }

        return builder.toString();
    }

    public String getSectionClass() {
        final String sectionClass;

        if (isSection() && classification.isHasClassifications()) {
            sectionClass = StringUtils.join(classification.getClassificationNames(), " ");
        } else {
            sectionClass = "";
        }

        return sectionClass;
    }

    public String getContainerElement() {
        return Elements.DIV;
    }

    public boolean isHasRole() {
        return getRole() != null;
    }

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

    public boolean isHasDomId() {
        return StringUtils.isNotBlank(getDomId());
    }

    public String getAuthorHelpMessage() {
        return "Content Section";
    }

    protected String getContainerFullWidthClass() {
        return Bootstrap.CONTAINER_FULL_WIDTH_CLASS;
    }

    protected String getContainerDefaultClass() {
        return Bootstrap.CONTAINER_CLASS;
    }

    /**
     * Indicates whether the author configured properties of a container component instance should
     * inherit from parent pages
     *
     * @return boolean
     */
    public boolean isInherits() {
        return false;
    }

    @Override
    public boolean isUsesResponsiveGrid() {
        return this.getContainerDesign().isUsesResponsiveGrid();
    }

    protected ContainerDesign getContainerDesign() {
        if (this.containerDesign == null) {
            this.containerDesign = this.getResource().adaptTo(ContainerDesign.class);
        }

        return this.containerDesign;
    }

}
