package com.citytechinc.cq.harbor.components.content.list.demo;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.constants.lists.ListConstants;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Demo Page List", group = "Harbor Lists", resourceSuperType = AbstractListComponent.RESOURCE_TYPE, name = "lists/demopagelist" )
@AutoInstantiate( instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME )
public class PageList extends AbstractListComponent<PageDecorator> {

    @DialogField
    @DialogFieldSet
    private final ChildPageListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet
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
    protected ListRenderingStrategy<PageDecorator> getListRenderingStrategy() {
        return renderingStrategy;
    }

    @Override
    public Boolean getIsUnorderedList() {
        return true;
    }
}
