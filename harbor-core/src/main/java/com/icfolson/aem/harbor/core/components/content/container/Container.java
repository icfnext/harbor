package com.icfolson.aem.harbor.core.components.content.container;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.InheritedClassification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;

@Component(
    value = "Container",
    description = "A container in which content may be placed.  All content should be placed in a container element.",
    name = "contentcontainer",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class)
public class Container extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/contentcontainer";

    public static final String FULL_WIDTH_PROPERTY = "fullWidth";

    private Classification classification;

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
    public Boolean getIsContainerFullWidth() {
        if (isInherits()) {
            return getInherited(FULL_WIDTH_PROPERTY, false);
        }

        return get(FULL_WIDTH_PROPERTY, false);
    }

    @DialogField(fieldLabel = "Container Inheritance",
        fieldDescription = "When enabled, an inheriting paragraph system is produced for this container instance.",
        ranking = 10)
    @Switch(offText = "No", onText = "Yes")
    public boolean isParsysInherits() {
        if (isInherits()) {
            return getInherited("parsysInherits", false);
        }

        return get("parsysInherits", false);
    }

    @DialogField(ranking = 20)
    @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

    @DialogField(fieldLabel = "ID",
        fieldDescription = "A unique identifier to apply to the Container element rendered in the page DOM.  If left blank, no id attribute will be applied to the rendered element.", tab = 2)
    @TextField
    public String getDomId() {
        if (isInherits()) {
            return getInherited("domId", StringUtils.EMPTY);
        }

        return get("domId", StringUtils.EMPTY);
    }

    public String getContainerClass() {
        final StringBuilder classStringBuffer = new StringBuilder();

        if (getIsContainerFullWidth()) {
            classStringBuffer.append(getContainerFullWidthClass());
        } else {
            classStringBuffer.append(getContainerDefaultClass());
        }

        if (!isSection()) {
            if (classification.getHasClassifications()) {
                classStringBuffer.append(" ").append(StringUtils.join(classification.getClassificationNames(), " "));
            }
        }

        return classStringBuffer.toString();
    }

    public String getSectionClass() {
        if (isSection()) {
            if (classification.getHasClassifications()) {
                return StringUtils.join(classification.getClassificationNames(), " ");
            }
        }

        return StringUtils.EMPTY;
    }

    public String getContainerElement() {
        return Elements.DIV;
    }

    public Optional<String> getRoleOptional() {
        return Optional.absent();
    }

    public Boolean getHasRole() {
        return getRoleOptional().isPresent();
    }

    public String getRole() {
        return getRoleOptional().orNull();
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
    protected boolean isInherits() {
        return false;
    }
}
