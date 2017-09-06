package com.icfolson.aem.harbor.core.components.content.calltoaction.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.models.annotations.Default;

import javax.inject.Inject;

public abstract class AbstractCallToAction {

    public static final String BUTTON_ID_PREFIX = "callToActionButton-";

    @Inject
    private ComponentNode componentNode;

    @Inject @Default(values = "Call to Action")
    private String text;

    @Inject @Default(values = Bootstrap.BTN_DEFAULT)
    private String size;

    @Inject @Default(values = "")
    private String style;

    @DialogField(fieldLabel = "Text") @TextField
    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }

    public String getId() {
        return BUTTON_ID_PREFIX + componentNode.getId();
    }

    protected ComponentNode getComponentNode() {
        return componentNode;
    }

}
