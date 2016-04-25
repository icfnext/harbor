package com.icfolson.aem.harbor.core.components.content.tabs;

import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
        value = "Tab",
        name = "tabs/tab",
        actions = { "text: Tab" ,"-", "edit", "delete" },
        listeners = {
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT") },
        group = ".hidden"
)
@AutoInstantiate(instanceName = "tab")
@Model(adaptables = Resource.class)
public class Tab extends AbstractComponent {

    public static final String TYPE = "harbor/components/content/tabs/tab";

    @Inject
    private PageDecorator currentPage;

    @DialogField(fieldLabel = "Title", fieldDescription = "The title to be presented within the Tab")
    public String getTitle() {
        return IconUtils.iconify(this.get("title", this.getName()));
    }

    public String getName() {
        return getResource().getName();
    }

    public String getUniqueId() {
        return Tabs.constructUniqueId(currentPage, getResource());
    }

    public boolean isGhost() {
        return !getResource().getResourceType().equals(TYPE);
    }
}