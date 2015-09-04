package com.citytechinc.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.aem.harbor.core.components.content.list.AbstractListComponent;
import com.citytechinc.aem.harbor.core.components.content.page.TrailPage;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.content.page.lists.construction.PageTrailListConstructionStrategy;

@Component(value = "Breadcrumb", group = ComponentGroups.HARBOR_NAVIGATION)
@AutoInstantiate(instanceName = "breadcrumb")
public class Breadcrumb extends AbstractListComponent<TrailPage, BreadcrumbTrail> {

	public static final String RESOURCE_TYPE = "harbor/components/content/breadcrumb";

	@DialogField(ranking = 2)
	@DialogFieldSet(border = false, collapsible = false)
	BreadcrumbItemRenderingStrategy breadcrumbItemListRenderingStrategy;

	@DialogField(ranking = 1)
	@DialogFieldSet(border = false, collapsible = false)
	PageTrailListConstructionStrategy breadcrumbItemListConstructionStrategy;

	@Override
	public void init(final ComponentRequest request) {
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
