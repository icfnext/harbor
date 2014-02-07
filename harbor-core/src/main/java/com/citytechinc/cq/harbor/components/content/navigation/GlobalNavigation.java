package com.citytechinc.cq.harbor.components.content.navigation;


import com.citytechinc.cq.component.annotations.*;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.content.page.impl.DefaultHierarchicalPage;
import com.citytechinc.cq.harbor.content.page.impl.DefaultHomePage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Global Navigation",
        actions = {"text: Global Navigation", "-", "edit", "-", "delete"},
        contentAdditionalProperties = {
                @ContentProperty(name="dependencies", value="harbor.components.content.navigation")
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
@AutoInstantiate( instanceName = "globalNavigation" )
public class GlobalNavigation extends AbstractComponent {
    private final DefaultHomePage homePage;

    public GlobalNavigation(ComponentRequest request) {
        super(request);
        //Retrieve homepage object out of the current path
        DefaultHierarchicalPage currentPage = new DefaultHierarchicalPage(request.getCurrentPage());
        homePage = new DefaultHomePage(currentPage.getHomePage().get());

        if(getAutoGenerateNavigation()){
            //Automatically Generate Navigation Tree

        }
        else{
            //Initialize list of manual navigation elements

        }
    }

    @DialogField(fieldLabel = "Auto Generate Navigation?",
            fieldDescription = "")
    @Selection(type=Selection.CHECKBOX, options = {
            @Option(text="", value = "true")
    })
    public Boolean getAutoGenerateNavigation(){
        return get("autoGenerateNavigation", "").equals("true");
    }
}
