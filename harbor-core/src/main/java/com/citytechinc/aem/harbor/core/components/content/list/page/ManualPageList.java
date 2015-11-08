package com.citytechinc.aem.harbor.core.components.content.list.page;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.harbor.api.constants.lists.ListConstants;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.aem.harbor.core.components.content.list.AbstractListComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Component(
        value = "Manual Page List",
        group = ComponentGroups.HARBOR_LISTS,
        resourceSuperType = AbstractListComponent.RESOURCE_TYPE, //TODO: Consider having this extend PageList as the jsp for both is basically the same
        name = "lists/manualpagelist")
@AutoInstantiate(instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME)
@Model(adaptables = Resource.class)
public class ManualPageList extends AbstractListComponent<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField
    @DialogFieldSet(title = "List Construction")
    @Inject @Self
    private ManualPageListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet(title = "List Rendering")
    @Inject @Self
    private LinkablePageRenderingStrategy renderingStrategy;

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
