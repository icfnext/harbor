package com.icfolson.aem.harbor.core.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.api.node.ComponentNode;
import org.apache.sling.models.annotations.Default;

import javax.inject.Inject;

public abstract class AbstractCallToAction {

    public static final String BUTTON_ID_PREFIX = "callToActionButton-";

    public static final String MODAL_ID_PREFIX = "callToActionModal-";

    public static final String CONTAINER_PAR_ID_PREFIX = "container-par-";

    @Inject
    protected ComponentNode componentNode;

    @DialogField(fieldLabel = "Text", fieldDescription = "Provide the widget's text", ranking = 0)
    @TextField
    @Inject
    @Default(values = "Call to Action")
    private String text;

    @DialogField(fieldLabel = "Size", fieldDescription = "Select the widget's size", ranking = 2)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = Bootstrap.BTN_DEFAULT),
        @Option(text = "Large", value = Bootstrap.BTN_LARGE),
        @Option(text = "Small", value = Bootstrap.BTN_SMALL),
        @Option(text = "Mini", value = Bootstrap.BTN_MINI)
    })
    @Inject
    @Default(values = Bootstrap.BTN_DEFAULT)
    private String size;

    @DialogField(fieldLabel = "Style", fieldDescription = "Select the widget's style", ranking = 3)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", qtip = "Standard basic button style, not tied to a semantic action or use",
            value = Bootstrap.BTN_DEFAULT),
        @Option(text = "Primary",
            qtip = "Provides extra visual weight and identifies the primary action in a set of buttons",
            value = Bootstrap.BTN_PRIMARY),
        @Option(text = "Info", qtip = "Used as an alternative to the default styles", value = Bootstrap.BTN_INFO),
        @Option(text = "Success", qtip = "Indicates a successful or positive action", value = Bootstrap.BTN_SUCCESS),
        @Option(text = "Warning", qtip = "Indicates caution should be taken with this action",
            value = Bootstrap.BTN_WARNING),
        @Option(text = "Danger", qtip = "Indicates a dangerous or potentially negative action",
            value = Bootstrap.BTN_DANGER),
        @Option(text = "Inverse", qtip = "Alternate to the default button style, not tied to a semantic action or use",
            value = Bootstrap.BTN_INVERSE),
        @Option(text = "Link",
            qtip = "Indicates that this button represents a simple link to a target resource or page",
            value = Bootstrap.BTN_LINK)
    })
    @Inject
    @Default(values = "")
    private String style;

    public String getText() {
        return IconUtils.iconify(text);
    }

    public String getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }

    public String getButtonId() {
        return BUTTON_ID_PREFIX + componentNode.getId();
    }

    public String getModalId() {
        return MODAL_ID_PREFIX + componentNode.getId();
    }

    public String getContainerParId() {
        return CONTAINER_PAR_ID_PREFIX + componentNode.getId();
    }
}
