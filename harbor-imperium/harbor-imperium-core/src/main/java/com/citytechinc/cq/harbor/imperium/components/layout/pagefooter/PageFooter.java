package com.citytechinc.cq.harbor.imperium.components.layout.pagefooter;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.imperium.components.layout.container.Container;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.api.constants.ontology.Roles;
import com.google.common.base.Optional;

@Component(value = "Page Footer", group = "Harbor Layout", resourceSuperType = Container.RESOURCE_TYPE, path = "layout", layout = "rollover")
@AutoInstantiate(instanceName = "contentContainer")
public class PageFooter extends Container {

	@Override
	public Optional<String> getRoleOptional() {
		return Optional.fromNullable(Roles.PAGE_FOOTER);
	}

	@Override
	public String getContainerElement() {
		return Elements.FOOTER;
	}

	@Override
	public String getContainerName() {
		return "Footer Container";
	}

    @Override
    public boolean isSection() {
        return false;
    }

}