package com.icfolson.aem.harbor.core.components.content.pagefooter.v1;

import com.icfolson.aem.harbor.api.components.content.container.Container;
import com.icfolson.aem.harbor.api.constants.dom.Elements;
import com.icfolson.aem.harbor.api.constants.ontology.Roles;
import com.icfolson.aem.harbor.core.components.content.container.v1.DefaultContainer;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

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

}
