package com.citytechinc.cq.harbor.components.content.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Tab",
        name = "tabs/tab",
        actions = {"text: Tab", "edit", "delete"},
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        },
        group = ".hidden"
)
@AutoInstantiate(instanceName = "tab")
public class Tab extends AbstractComponent {

    public static final String TYPE = "harbor/components/content/tabs/tab";

    public Tab(ComponentRequest request) {
        this(request.getComponentNode());
    }

    public Tab(ComponentNode componentNode) {
        super(componentNode);
    }

    @DialogField(fieldLabel = "Title", fieldDescription = "The title to be presented within the Tab")
    public String getTitle() {
        return this.get("title", this.getName());
    }

    public String getName() {
        return this.getResource().getName();
    }

    public String getUniqueId() {
        return Tabs.constructUniqueId(this.currentPage, this.getResource());
    }
}