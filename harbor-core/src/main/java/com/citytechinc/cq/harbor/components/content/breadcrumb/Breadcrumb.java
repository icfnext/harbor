package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.constants.lists.ListConstants;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Breadcrumb",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.bootstrap]"),
        })
@AutoInstantiate( instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME )
public class Breadcrumb extends AbstractListComponent<BreadcrumbItem> {

    @DialogField(ranking = 1)
    @DialogFieldSet(border = false, collapsible = false)
    BreadcrumbItemRenderingStrategy breadcrumbItemListRenderingStrategy;

    @DialogField(ranking = 2)
    @DialogFieldSet(border = false, collapsible = false)
    BreadcrumbItemListConstructionStrategy breadcrumbItemListConstructionStrategy;

    public Breadcrumb(final ComponentRequest request) {
        super(request);
        breadcrumbItemListConstructionStrategy = new BreadcrumbItemListConstructionStrategy(request);
        breadcrumbItemListRenderingStrategy = new BreadcrumbItemRenderingStrategy(request);
    }

    @Override
    protected ListConstructionStrategy<BreadcrumbItem> getListConstructionStrategy() {
        return breadcrumbItemListConstructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<BreadcrumbItem> getListRenderingStrategy() {
        return breadcrumbItemListRenderingStrategy;
    }
}
