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
import org.apache.sling.models.annotations.Default;
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

    @DialogField(fieldLabel = "Action", fieldDescription = "Select the widget's action upon being clicked", ranking = 4)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Open in the Current Window", qtip = "Opens link to specified path in current window.",
                    value = LINK_IN_CURRENT),
            @Option(text = "Open in a New Window/Tab", qtip = "Opens link to specified path in a new window or tab.",
                    value = LINK_IN_WINDOW)
    })
    @Inject
    @Default(values = "")
    private String action;

    @DialogField(fieldLabel = "Link Target", fieldDescription = "URL path this button leads to", ranking = 2)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public Link getLinkTarget() {
        return componentNode.getAsLink("linkTarget").or(currentPage.getLink());
    }

    public String getAction() {
        return action;
    }

    public Boolean isOpensInNewWindow() {
        return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_WINDOW);
    }

    public Boolean isOpensInCurrentWindow() {
        return StringUtils.equalsIgnoreCase(getAction(), LINK_IN_CURRENT);
    }
}
