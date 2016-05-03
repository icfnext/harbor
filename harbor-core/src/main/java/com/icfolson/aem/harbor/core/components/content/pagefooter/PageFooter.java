package com.icfolson.aem.harbor.core.components.content.pagefooter;

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
		value = "Page Footer",
		group = ComponentGroups.HARBOR_SCAFFOLDING,
		resourceSuperType = Container.RESOURCE_TYPE,
		tabs = {
				@Tab(title = "Container"),
				@Tab(title = "Advanced")
		})
@Model(adaptables = Resource.class)
public class PageFooter extends Container {

	public static final String RESOURCE_TYPE = "harbor/components/content/pagefooter";

	@Override
	public Optional<String> getRoleOptional() {
		return Optional.fromNullable(Roles.PAGE_FOOTER);
	}

	@Override
	public String getContainerElement() {
		return Elements.FOOTER;
	}

    @Override
    protected boolean isInherits() {
        return true;
    }

	@Override
	public String getAuthorHelpMessage() {
		return "Page Footer";
	}

}
