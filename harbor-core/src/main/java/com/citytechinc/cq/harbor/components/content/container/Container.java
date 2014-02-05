package com.citytechinc.cq.harbor.components.content.container;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.bootstrap.Bootstrap;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Container", description = "A container in which content may be placed.  All content should be placed in a container element.", name = "contentcontainer" )
public class Container extends AbstractComponent {

    public static final String FULL_WIDTH_PROPERTY = "fullWidth";

    public Container(ComponentRequest request) {
        super(request);
    }

    @DialogField( fieldLabel = "Full Width", fieldDescription = "When set to true, the container will render across the full width of the browser window", name = "./" + FULL_WIDTH_PROPERTY )
    @Selection( options = { @Option( text = "true", value = "true" ) }, type = Selection.CHECKBOX )
    public Boolean getIsContainerFullWidth() {

        return get(FULL_WIDTH_PROPERTY, false);

    }

    public String getContainerClass() {

        if (getIsContainerFullWidth()) {
            return getContainerFullWidthClass();
        }

        return getContainerDefaultClass();

    }

    protected String getContainerFullWidthClass() {
        return Bootstrap.CONTAINER_FULL_WIDTH_CLASS;
    }

    protected String getContainerDefaultClass() {
        return Bootstrap.CONTAINER_CLASS;
    }

}
