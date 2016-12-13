package com.icfolson.aem.harbor.core.components.content.list.page;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.constants.PathConstants;

import java.util.List;

public class ChildPageListConstructionStrategy implements ListConstructionStrategy<PageDecorator> {

    private final ComponentNode componentNode;

    public ChildPageListConstructionStrategy(ComponentNode componentNode) {
        this.componentNode = componentNode;
    }

    @DialogField(fieldLabel = "Start Page", name = "./startPagePath")
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public Optional<PageDecorator> getStartPage() {
        return componentNode.getAsPage("startPagePath");
    }

    @Override
    public List<PageDecorator> construct() {
        return getStartPage().transform(PageDecorator:: getChildren).or(Lists.newArrayList());
    }
}
