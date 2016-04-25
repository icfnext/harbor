package com.icfolson.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.page.TrailPage;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.content.page.lists.construction.InheritingPageTrailListConstructionStrategy;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Inheriting Breadcrumb", group = ComponentGroups.HARBOR_NAVIGATION, resourceSuperType = BreadcrumbComponent.RESOURCE_TYPE, suppressFieldInheritanceForTouchUI = true, tabs = {@Tab(title = "Breadcrumb", touchUINodeName = "breadcrumb")})
@AutoInstantiate(instanceName = "breadcrumb")
@Model(adaptables = Resource.class)
public class InheritingBreadcrumb extends BreadcrumbComponent {

    public static final String RESOURCE_TYPE = "harbor/components/content/inheritingbreadcrumb";

    @DialogField(ranking = 2)
    @DialogFieldSet(border = false, collapsible = false)
    @Inject
    @Self
    InheritingBreadcrumbItemRenderingStrategy breadcrumbItemListRenderingStrategy;

    @DialogField(ranking = 1)
    @DialogFieldSet(border = false, collapsible = false)
    @Inject @Self
    InheritingPageTrailListConstructionStrategy breadcrumbItemListConstructionStrategy;

    @Override
    protected ListConstructionStrategy<TrailPage> getListConstructionStrategy() {
        return breadcrumbItemListConstructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<TrailPage, BreadcrumbTrail> getListRenderingStrategy() {
        return breadcrumbItemListRenderingStrategy;
    }
}
