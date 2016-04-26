package com.icfolson.aem.harbor.core.components.content.pageheader;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.api.constants.ontology.Roles;
import com.icfolson.aem.harbor.core.components.content.container.Container;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Tab;
import com.google.common.base.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
	value = "Page Header",
	group = ComponentGroups.HARBOR_SCAFFOLDING,
	resourceSuperType = Container.RESOURCE_TYPE,
    tabs = {
            @Tab(title = "Container"),
            @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class)
public class PageHeader extends Container {

	public static final String RESOURCE_TYPE = "harbor/components/content/pageheader";

	@Override
	public Optional<String> getRoleOptional() {
		return Optional.fromNullable(Roles.PAGE_HEADER);
	}

	@Override
	public String getContainerElement() {
		return Elements.HEADER;
	}

    @Override
    public String getAuthorHelpMessage() {
        return "Page Header";
    }

    @Override
    protected boolean isInherits() {
        return true;
    }


}
