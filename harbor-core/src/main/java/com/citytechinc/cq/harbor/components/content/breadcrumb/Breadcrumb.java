package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.content.page.lists.construction.PageTrailListConstructionStrategy;
import com.citytechinc.cq.harbor.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Breadcrumb",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.bootstrap]"),
        } )
@AutoInstantiate( instanceName = "breadcrumb" )
public class Breadcrumb extends AbstractListComponent<BreadcrumbPage, BreadcrumbTrail> {

    @DialogField(ranking = 2)
    @DialogFieldSet(border = false, collapsible = false)
    BreadcrumbItemRenderingStrategy breadcrumbItemListRenderingStrategy;

    @DialogField(ranking = 1)
    @DialogFieldSet(border = false, collapsible = false)
    PageTrailListConstructionStrategy breadcrumbItemListConstructionStrategy;

    public Breadcrumb(final ComponentRequest request) {
        super(request);
        breadcrumbItemListConstructionStrategy = new PageTrailListConstructionStrategy(request);
        breadcrumbItemListRenderingStrategy = new BreadcrumbItemRenderingStrategy(request);
    }

    @Override
    protected ListConstructionStrategy<BreadcrumbPage> getListConstructionStrategy() {
        return breadcrumbItemListConstructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<BreadcrumbPage, BreadcrumbTrail> getListRenderingStrategy() {
        return breadcrumbItemListRenderingStrategy;
    }

}
