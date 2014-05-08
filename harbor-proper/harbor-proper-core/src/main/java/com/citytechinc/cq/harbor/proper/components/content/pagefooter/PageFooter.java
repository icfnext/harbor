package com.citytechinc.cq.harbor.proper.components.content.pagefooter;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.proper.components.content.container.Container;
import com.citytechinc.cq.harbor.proper.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.constants.ontology.Roles;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;

@Component( value = "Page Footer", group = "Harbor Scaffolding", resourceSuperType = Container.RESOURCE_TYPE )
public class PageFooter extends Container {

    public PageFooter(ComponentRequest request) {
        super(request);
    }

    @Override
    public Optional<String> getRoleOptional() {
        return Optional.fromNullable(Roles.PAGE_FOOTER);
    }

    @Override
    public String getContainerElement() {
        return Elements.FOOTER;
    }

}
