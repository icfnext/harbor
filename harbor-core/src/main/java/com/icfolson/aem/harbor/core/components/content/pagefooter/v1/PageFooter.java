package com.icfolson.aem.harbor.core.components.content.pagefooter.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.api.constants.ontology.Roles;
import com.icfolson.aem.harbor.core.components.content.container.v1.DefaultContainer;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Page Footer (v1)",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    resourceSuperType = DefaultContainer.RESOURCE_TYPE,
    isContainer = true,
    name = "pagefooter/v1/pagefooter",
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class, adapters = Container.class, resourceType = PageFooter.RESOURCE_TYPE)
public class PageFooter extends DefaultContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/pagefooter/v1/pagefooter";

    @Override
    public String getRole() {
        return Roles.PAGE_FOOTER;
    }

    @Override
    public String getContainerElement() {
        return Elements.FOOTER;
    }

    @Override
    public boolean isInherits() {
        return true;
    }

    @Override
    public String getAuthorHelpMessage() {
        return "Page Footer";
    }

}
