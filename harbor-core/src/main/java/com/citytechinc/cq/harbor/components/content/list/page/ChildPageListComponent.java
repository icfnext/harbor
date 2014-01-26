package com.citytechinc.cq.harbor.components.content.list.page;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

@Component(value = "Direct Child Page List", group = "Harbor Lists", path = "content/lists")
public class ChildPageListComponent extends AbstractListComponent<PageDecorator, PageDecorator, PageDecorator> {

    public ChildPageListComponent(ComponentRequest componentRequest) {
        super(componentRequest);
    }

    @Override
    public Optional<PageDecorator> getRootOptional() {
        return Optional.fromNullable(currentPage);
    }

    @Override
    protected List<PageDecorator> buildList() {

        if (isHasRoot()) {
            return getRootOptional().get().getChildren();
        }

        return Lists.newArrayList();

    }

}
