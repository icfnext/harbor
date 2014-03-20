package com.citytechinc.cq.harbor.components.content.navigation.bootstrapnavigation;

import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.navigation.constructionstrategy.PageTreeConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.tree.*;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component( value = "Bootstrap Main Manual Navigation",
        group = "Harbor Scaffolding",
        actions = {"text:Bootstrap Main Manual Navigation", "-", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.globalnavigation")
        },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Navigation Column", handler = "function(){ Harbor.Components.GlobalNavigation.addNavigationElement(this) }"),
        },
        listeners = {
                @Listener(name = "afterinsert", value = "REFRESH_PAGE")
        },
        allowedParents = "*/parsys"
)
@AutoInstantiate( instanceName = "bootstrapMainManualNavigation" )
public class BootstrapMainManualNavigation extends AbstractComponent {

    private List<BootstrapMainNavigationElement> bootstrapMainNavigationElementList;

    public BootstrapMainManualNavigation(ComponentRequest request) {
        super(request);

        bootstrapMainNavigationElementList = new ArrayList<BootstrapMainNavigationElement>();
        //Add The child elements of our GlobalNav to the Nav Element list
        Iterator<Resource> navigationResourceIterator = request.getResource().listChildren();

        while (navigationResourceIterator.hasNext()) {
            this.bootstrapMainNavigationElementList.add(new BootstrapMainNavigationElement(navigationResourceIterator.next().adaptTo(ComponentNode.class)));
        }
    }

    @DialogField(fieldLabel = "Enable Sticky Navigation?",
            fieldDescription = "")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getStickyNavigationEnabled(){
        return get("stickyNavigationEnabled", "").equals("true");
    }

    public List<BootstrapMainNavigationElement> getBootstrapMainNavigationElementList(){
        return bootstrapMainNavigationElementList;
    }


}
