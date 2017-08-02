package com.icfolson.aem.harbor.core.components.content.pageheader.v1;

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
    value = "Page Header (v1)",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    resourceSuperType = DefaultContainer.RESOURCE_TYPE,
    isContainer = true,
    name = "pageheader/v1/pageheader",
    tabs = {
        @Tab(title = "Container"),
        @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class, adapters = Container.class, resourceType = PageHeader.RESOURCE_TYPE)
public class PageHeader extends DefaultContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/pageheader/v1/pageheader";

    @Override
    public String getRole() {
        return Roles.PAGE_HEADER;
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
    public boolean isInherits() {
        return true;
    }

}
