package com.citytechinc.cq.harbor.proper.core.components.content.pageheader;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.api.constants.ontology.Roles;
import com.citytechinc.cq.harbor.proper.core.components.content.container.Container;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;

@Component( value = "Page Header", group = "Harbor Scaffolding", resourceSuperType = Container.RESOURCE_TYPE )
public class PageHeader extends Container {

    public PageHeader(ComponentRequest request) {
        super(request);
    }

    @Override
    public Optional<String> getRoleOptional() {
        return Optional.fromNullable(Roles.PAGE_HEADER);
    }

    @Override
    public String getContainerElement() {
        return Elements.HEADER;
    }
}
