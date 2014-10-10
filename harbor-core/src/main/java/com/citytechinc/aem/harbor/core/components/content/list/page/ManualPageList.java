package com.citytechinc.aem.harbor.core.components.content.list.page;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.harbor.api.constants.lists.ListConstants;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.aem.harbor.core.components.content.list.AbstractListComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;

import java.util.List;

@Component(
        value = "Manual Page List",
        group = ComponentGroups.HARBOR_LISTS,
        resourceSuperType = AbstractListComponent.RESOURCE_TYPE, //TODO: Consider having this extend PageList as the jsp for both is basically the same
        name = "lists/manualpagelist")
@AutoInstantiate(instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME)
public class ManualPageList extends AbstractListComponent<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField
    @DialogFieldSet(title = "List Construction")
    private ManualPageListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet(title = "List Rendering")
    private LinkablePageRenderingStrategy renderingStrategy;

    @Override
    public void init(ComponentRequest request) {
        constructionStrategy = getComponent(this, ManualPageListConstructionStrategy.class);
        renderingStrategy = new LinkablePageRenderingStrategy(this);
    }

    @Override
    protected ListConstructionStrategy<PageDecorator> getListConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> getListRenderingStrategy() {
        return renderingStrategy;
    }

    @Override
    public Boolean getIsUnorderedList() {
        return true;
    }

}
