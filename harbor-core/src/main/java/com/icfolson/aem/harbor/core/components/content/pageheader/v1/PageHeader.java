package com.icfolson.aem.harbor.core.components.content.pageheader.v1;

import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.api.constants.ontology.Roles;
import com.icfolson.aem.harbor.core.components.content.container.v1.DefaultContainer;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

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
    public boolean isInherits() {
        return true;
    }

}
