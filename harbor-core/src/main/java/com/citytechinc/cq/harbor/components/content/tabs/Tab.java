package com.citytechinc.cq.harbor.components.content.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.harbor.components.content.tabs.Tabs;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Tab",
        actions = {"text: Tab", "edit", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "harbor.components.content.tabs")
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        }
)
public class Tab extends AbstractComponent {

    public static final String TYPE = "harbor/components/content/tabs/tab";
    private final String title;
    private final String name;
    private final String uniqueId;

    public Tab(ComponentRequest request) {
        this(request.getComponentNode());
    }

    public Tab(ComponentNode componentNode) {
        super(componentNode);

        this.name = componentNode.getResource().getName();
        this.title = this.get("title", this.name);
        //TODO: fix this once Paul decides where to keep the unique Id generator
        this.uniqueId = Tabs.constructUniqueId(componentNode.getResource());

    }

    @DialogField(name = "title", fieldLabel = "Title", fieldDescription = "The title to be presented within the Tab")
    public String getTitle() {
        return this.title;
    }

    public String getName() {
        return this.name;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }
}
