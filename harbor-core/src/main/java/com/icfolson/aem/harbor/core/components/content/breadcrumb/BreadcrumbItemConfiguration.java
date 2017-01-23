package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.library.api.node.ComponentNode;

public final class BreadcrumbItemConfiguration {

    public static final String HIDE_ICON_PROPERTY_NAME = "hideIcon";

    public static final String HIDE_TITLE_PROPERTY_NAME = "hideTitle";

    private final ComponentNode componentNode;

    private final String propertyNamePrefix;

    private final boolean inherits;

    public BreadcrumbItemConfiguration(final ComponentNode componentNode, final String propertyNamePrefix,
        final boolean inherits) {
        this.componentNode = componentNode;
        this.propertyNamePrefix = propertyNamePrefix.substring(2);
        this.inherits = inherits;
    }

    @DialogField(fieldLabel = "Hide Icon")
    @Switch(offText = "No", onText = "Yes")
    public Boolean getHideIcon() {
        return inherits ? componentNode.getInherited(propertyNamePrefix + HIDE_ICON_PROPERTY_NAME,
            false) : componentNode.get(propertyNamePrefix + HIDE_ICON_PROPERTY_NAME, false);
    }

    @DialogField(fieldLabel = "Hide Title")
    @Switch(offText = "No", onText = "Yes")
    public Boolean getHideTitle() {
        return inherits ? componentNode.getInherited(propertyNamePrefix + HIDE_TITLE_PROPERTY_NAME,
            false) : componentNode.get(propertyNamePrefix + HIDE_TITLE_PROPERTY_NAME, false);
    }
}
