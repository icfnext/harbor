package com.citytechinc.aem.harbor.core.components.content.list.link;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.link.Link;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.link.builders.factory.LinkBuilderFactory;
import com.citytechinc.aem.harbor.core.components.mixins.classifiable.Classification;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.google.common.collect.Lists;

import java.util.List;

@Component(
        value = "Link List",
        group = ComponentGroups.HARBOR_LISTS,
        name = "lists/linklist",
        actions = { "text:Link List", "edit", "-", "copymove", "delete", "insert" },
        contentAdditionalProperties = { @ContentProperty(name = "dependencies", value = "[harbor.components.content.linklist,harbor.fontawesome]") },
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(
                        text = "Add Link",
                        handler = "function() { Harbor.Components.LinkList.addLink( this ); }",
                        additionalProperties = {@ActionConfigProperty(name = "icon", value = "coral-Icon--add")})}
)
@AutoInstantiate(instanceName = "linklist")
public class LinkList extends AbstractComponent {

    private List<ListableLink> links;
    private Classification classification;

    @DialogField
    @DialogFieldSet
    public Classification getClassification() {
        if (classification == null) {
            classification = getComponent(this, Classification.class);
        }

        return classification;
    }

    public List<ListableLink> getLinks() {

        if (links == null) {
            links = Lists.newArrayList();

            for(ComponentNode currentLinkNode : getComponentNodes()) {
                links.add(getComponent(currentLinkNode, ListableLink.class));
            }
        }

        return links;

    }

}
