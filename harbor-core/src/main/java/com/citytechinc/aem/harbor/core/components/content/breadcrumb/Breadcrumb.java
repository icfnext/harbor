package com.citytechinc.aem.harbor.core.components.content.breadcrumb;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.aem.harbor.core.components.content.list.AbstractListComponent;
import com.citytechinc.aem.harbor.core.components.content.page.TrailPage;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.content.page.lists.construction.PageTrailListConstructionStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Breadcrumb", group = ComponentGroups.HARBOR_NAVIGATION)
@AutoInstantiate(instanceName = "breadcrumb")
@Model(adaptables = Resource.class)
public class Breadcrumb extends AbstractListComponent<TrailPage, BreadcrumbTrail> {

	public static final String RESOURCE_TYPE = "harbor/components/content/breadcrumb";

	@DialogField(ranking = 2)
	@DialogFieldSet(border = false, collapsible = false)
	@Inject @Self
	BreadcrumbItemRenderingStrategy breadcrumbItemListRenderingStrategy;

	@DialogField(ranking = 1)
	@DialogFieldSet(border = false, collapsible = false)
	@Inject @Self
	PageTrailListConstructionStrategy breadcrumbItemListConstructionStrategy;

	@Override
	protected ListConstructionStrategy<TrailPage> getListConstructionStrategy() {
		return breadcrumbItemListConstructionStrategy;
	}

	@Override
	protected ListRenderingStrategy<TrailPage, BreadcrumbTrail> getListRenderingStrategy() {
		return breadcrumbItemListRenderingStrategy;
	}

}
