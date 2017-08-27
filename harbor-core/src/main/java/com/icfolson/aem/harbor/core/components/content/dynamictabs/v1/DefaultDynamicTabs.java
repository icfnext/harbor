package com.icfolson.aem.harbor.core.components.content.dynamictabs.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab;
import com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTabs;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        value = "Dynamic Tabs (v1)",
        actions = { "text: Dynamic Tabs (v1)", "edit", "-", "copymove", "delete", "-", "insert" },
        isContainer = true,
        group = ComponentGroups.HARBOR_SCAFFOLDING,
        name = "dynamictabs/v1/dynamictabs",
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text = "Add Tab",
                        handler = "function() { Harbor.Components.DynamicTabs.v1.DynamicTabs.addTab( this, '" + "/apps/" + NewTab.RESOURCE_TYPE + "/" + NewTab.DIALOG_FILE_NAME + "' ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--experienceAdd")
                        } ),
                @ActionConfig(text = "Previous Slide",
                        handler = "function() { Harbor.Components.DynamicTabs.v1.DynamicTabs.previousTab( this ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--rewindCircle")
                        } ),
                @ActionConfig(text = "Next Slide",
                        handler = "function() { Harbor.Components.DynamicTabs.v1.DynamicTabs.nextTab( this ) }",
                        additionalProperties = {
                                @ActionConfigProperty(name = "icon", value = "coral-Icon--fastForwardCircle")
                        } )
        } )
@Model(adaptables = Resource.class, adapters = DynamicTabs.class, resourceType = DefaultDynamicTabs.RESOURCE_TYPE)
public class DefaultDynamicTabs {

    public static final String RESOURCE_TYPE = "harbor/components/content/dynamictabs/v1/dynamictabs";

    @Inject
    private Resource resource;

    @Inject @Self
    private Classification classification;

    public List<DynamicTab> getTabs() {
        //TODO: I feel like I should not have to do this - the injection of the list should be able to adapt to the tabs directly.  Check into http://svn.apache.org/repos/asf/sling/trunk/bundles/extensions/models/impl/src/main/java/org/apache/sling/models/impl/ModelAdapterFactory.java
        //Caused by: org.apache.sling.models.factory.ModelClassException: interface java.util.List is neither a parameterized Collection or List
        return Lists.newArrayList(resource.getChildren()).stream().map(r -> r.adaptTo(DynamicTab.class)).collect(Collectors.toList());
    }

    @DialogField(ranking = 20) @DialogFieldSet
    public Classification getClassification() {
        return classification;
    }

}
