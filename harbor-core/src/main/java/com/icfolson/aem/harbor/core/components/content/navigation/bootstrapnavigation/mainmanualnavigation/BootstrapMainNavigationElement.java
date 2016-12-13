package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.api.page.enums.TitleType;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import com.icfolson.aem.library.core.constants.PathConstants;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;
import com.icfolson.aem.library.models.annotations.InheritInject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Component(
    value = "Navigation Element",
    actions = { "text: Navigation Element", "-", "edit", "-", "delete" },
    listeners = {
        @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
        @Listener(name = "afteredit", value = "REFRESH_PARENT")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    layout = "rollover",
    path = "content/navigation/bootstrapmainmanualnavigation",
    htmlTag = @HtmlTag(tagName = "li"))
@Model(adaptables = Resource.class)
public class BootstrapMainNavigationElement extends AbstractComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapmainmanualnavigation/bootstrapmainnavigationelement";

    @DialogField(fieldLabel = "Element Title",
        fieldDescription = "If link target is an AEM page, this value overrides the page title.", ranking = 1)
    @TextField
    @InheritInject
    @Optional
    private String elementTitle;

    @DialogField(fieldLabel = "Element Link Target", ranking = 2, required = true)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    @InheritInject
    @Default(values = "#")
    private String elementLinkTarget;

    private Link elementLink;

    @DialogField(fieldLabel = "Has Dropdown?",
        fieldDescription = "This navigation element will be a dropdown/flyout element", ranking = 3)
    @Switch(offText = "No", onText = "Yes")
    public Boolean isHasDropdown() {
        return getInherited("hasDropdown", false);
    }

    public Link getElementLink() {
        if (elementLink == null) {
            final String pageTitle = getAsPageInherited("elementLinkTarget").transform(
                page -> page.getTitle(TitleType.NAVIGATION_TITLE).or(page.getTitle()))
                .or("Navigation Element " + (getIndex() + 1));

            elementLink = LinkBuilderFactory.forPath(elementLinkTarget)
                .setTitle(elementTitle != null ? elementTitle : pageTitle)
                .build();
        }

        return elementLink;
    }

    public String getRelativePath() {
        return "elements/" + getResource().getName();
    }

    public String getResourceType() {
        return isHasDropdown() ? BootstrapMainNavigationElementWithDropdown.RESOURCE_TYPE : RESOURCE_TYPE;
    }
}
