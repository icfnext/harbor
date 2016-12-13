package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainmanualnavigation;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.api.page.enums.TitleType;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class BootstrapMainNavigationLink extends AbstractComponent {

    @DialogField(fieldLabel = "Element Title",
        fieldDescription = "If link target is an AEM page, this value overrides the page title.", ranking = 1)
    @TextField
    @Inject
    @Optional
    private String title;

    @DialogField(fieldLabel = "Element Link", ranking = 2, required = true)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    @Inject
    @Default(values = "#")
    private String path;

    private Link link;

    @DialogField(fieldLabel = "Add Divider?",
        fieldDescription = "Select 'Yes' to add a divider above this element.", ranking = 3)
    @Switch(offText = "No", onText = "Yes")
    public boolean isAddDivider() {
        return get("addDivider", false);
    }

    public Link getLink() {
        if (link == null) {
            final String pageTitle = getAsPageInherited("path").transform(
                page -> page.getTitle(TitleType.NAVIGATION_TITLE).or(page.getTitle()))
                .or("Navigation Element " + (getIndex() + 1));

            link = LinkBuilderFactory.forPath(path)
                .setTitle(title != null ? title : pageTitle)
                .build();
        }

        return link;
    }
}
