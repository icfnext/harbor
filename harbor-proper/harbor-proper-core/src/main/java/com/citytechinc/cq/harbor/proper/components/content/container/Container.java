package com.citytechinc.cq.harbor.proper.components.content.container;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.components.mixins.classifiable.Classification;
import com.citytechinc.cq.harbor.proper.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.harbor.proper.constants.dom.Elements;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;

@Component( value = "Container", description = "A container in which content may be placed.  All content should be placed in a container element.", name = "contentcontainer" )
@AutoInstantiate( instanceName = Container.INSTANCE_NAME )
public class Container extends AbstractComponent {

    private Classification classification;

    public static final String RESOURCE_TYPE = "harbor/components/content/contentcontainer";
    public static final String INSTANCE_NAME = "contentContainer";

    public static final String FULL_WIDTH_PROPERTY = "fullWidth";

    public Container(ComponentRequest request) {
        super(request);
    }

    @DialogField( fieldLabel = "Full Width", fieldDescription = "When set to true, the container will render across the full width of the browser window", name = "./" + FULL_WIDTH_PROPERTY )
    @Selection( options = { @Option( text = "true", value = "true" ) }, type = Selection.CHECKBOX )
    public Boolean getIsContainerFullWidth() {

        return get(FULL_WIDTH_PROPERTY, false);

    }

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        if (classification == null) {
            classification = new Classification(this.request);
        }

        return classification;
    }

    public String getContainerClass() {

        StringBuffer classStringBuffer = new StringBuffer();

        if (getIsContainerFullWidth()) {
            classStringBuffer.append(getContainerFullWidthClass());
        }
        else {
            classStringBuffer.append(getContainerDefaultClass());
        }

        if (getClassification().getHasClassification()) {
            classStringBuffer.append(" ").append(getClassification().getClassificationName());
        }

        return classStringBuffer.toString();

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
        return getRoleOptional().get();
    }

    protected String getContainerFullWidthClass() {
        return Bootstrap.CONTAINER_FULL_WIDTH_CLASS;
    }

    protected String getContainerDefaultClass() {
        return Bootstrap.CONTAINER_CLASS;
    }



}
