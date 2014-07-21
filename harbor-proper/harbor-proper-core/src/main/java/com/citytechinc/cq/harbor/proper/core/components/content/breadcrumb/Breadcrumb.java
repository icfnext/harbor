package com.citytechinc.cq.harbor.proper.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.proper.core.components.content.page.TrailPage;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.harbor.proper.core.content.page.lists.construction.PageTrailListConstructionStrategy;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "Breadcrumb",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.bootstrap.breadcrumbs]"),
        },
        group = ComponentGroups.HARBOR_NAVIGATION )
@AutoInstantiate( instanceName = "breadcrumb" )
public class Breadcrumb extends AbstractListComponent<TrailPage, BreadcrumbTrail> {

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
    protected ListConstructionStrategy<TrailPage> getListConstructionStrategy() {
        return breadcrumbItemListConstructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<TrailPage, BreadcrumbTrail> getListRenderingStrategy() {
        return breadcrumbItemListRenderingStrategy;
    }

}
