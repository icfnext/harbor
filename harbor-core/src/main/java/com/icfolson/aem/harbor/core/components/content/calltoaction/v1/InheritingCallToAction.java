package com.icfolson.aem.harbor.core.components.content.calltoaction.v1;

import com.icfolson.aem.harbor.api.components.content.calltoaction.CallToAction;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, adapters = CallToAction.class, resourceType = InheritingCallToAction.RESOURCE_TYPE)
public class InheritingCallToAction extends DefaultCallToAction {

    public static final String RESOURCE_TYPE = DefaultCallToAction.RESOURCE_TYPE + "/inheriting";

    @InheritInject
    @Default(values = "Call to Action")
    private String text;

    @InheritInject @Default(values = Bootstrap.BTN_DEFAULT)
    private String size;

    @InheritInject @Default(values = "")
    private String style;

    @InheritInject @Default(values = "")
    private String action;

    @Override
    public String getAction() {
        return action;
    }

    public String getLinkHref() {
        return getComponentNode().getAsHrefInherited("linkHref").or(super.getLinkHref());
    }

    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }
}
