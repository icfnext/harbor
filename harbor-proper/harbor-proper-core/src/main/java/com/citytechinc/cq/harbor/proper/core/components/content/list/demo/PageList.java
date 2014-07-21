package com.citytechinc.cq.harbor.proper.core.components.content.list.demo;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.constants.lists.ListConstants;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractListComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

import java.util.List;

@Component( value = "Demo Page List", group = "Harbor Lists", resourceSuperType = AbstractListComponent.RESOURCE_TYPE, name = "lists/demopagelist" )
@AutoInstantiate( instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME )
public class PageList extends AbstractListComponent<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField
    @DialogFieldSet( title = "List Construction" )
    private final ChildPageListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet( title = "List Rendering" )
    private final LinkablePageRenderingStrategy renderingStrategy;

    public PageList(ComponentRequest request) {
        super(request);

        constructionStrategy = new ChildPageListConstructionStrategy(request.getComponentNode());
        renderingStrategy = new LinkablePageRenderingStrategy(request.getComponentNode());
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
