package com.icfolson.aem.harbor.core.components.content.calltoaction;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.icfolson.aem.harbor.api.components.content.calltoaction.CallToAction;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
        value = "Call to Action",
        group = ComponentGroups.HARBOR,
        name = "calltoaction/v1/calltoaction",
        layout = "rollover")
@Model(adaptables = Resource.class, adapters = CallToAction.class, resourceType = DefaultCallToAction.RESOURCE_TYPE)
public class DefaultCallToAction extends AbstractCallToAction implements CallToAction {

    public static final String RESOURCE_TYPE = "harbor/components/content/calltoaction/v1/calltoaction";

    @Inject
    private PageDecorator currentPage;

    public String getAction() {
        return get("action", "");
    }

    public Link getLinkTarget() {
        return getAsLink("linkTarget").or(currentPage.getLink());
    }

    public Boolean isOpensInNewWindow() {
        return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_WINDOW);
    }

    public Boolean isOpensInCurrentWindow() {
        return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_CURRENT);
    }
}
