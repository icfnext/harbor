package com.citytechinc.cq.harbor.components.content.list.demo;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.harbor.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

public class ChildPageListConstructionStrategy implements ListConstructionStrategy<PageDecorator> {

    @DialogField( fieldLabel = "Start Page", name = "./startPagePath" )
    @PathField
    private final Optional<PageDecorator> startPageOptional;

    public ChildPageListConstructionStrategy(ComponentNode componentNode) {
        PageManagerDecorator pageManagerDecorator = componentNode.getResource().getResourceResolver().adaptTo(PageManagerDecorator.class);

        Optional<String> startPagePathOptional = componentNode.get("startPagePath", String.class);

        if (startPagePathOptional.isPresent()) {
            startPageOptional = Optional.fromNullable(pageManagerDecorator.getContainingPage(startPagePathOptional.get()));
        }
        else {
            startPageOptional = Optional.absent();
        }
    }

    @Override
    public List<PageDecorator> construct() {

        if (startPageOptional.isPresent()) {
            return startPageOptional.get().getChildren();
        }

        return Lists.newArrayList();

    }
}
