package com.citytechinc.cq.harbor.proper.core.components.content.list.demo;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.collect.Lists;

import java.util.List;

public class LinkablePageRenderingStrategy implements ListRenderingStrategy<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField( fieldLabel = "Render as Link?" )
    @Selection( type = Selection.CHECKBOX, options = { @Option( text = "", value = "true" ) } )
    private final Boolean renderAsLink;

    public LinkablePageRenderingStrategy(ComponentNode componentNode) {
        renderAsLink = componentNode.get("renderAsLink", false);
    }

    public Boolean getRenderAsLink() {
        return renderAsLink;
    }

    @Override
    public List<LinkablePage> toRenderableList(Iterable<PageDecorator> itemIterable) {
        List<LinkablePage> retList = Lists.newArrayList();

        for (PageDecorator currentPageDecorator : itemIterable) {
            retList.add(new LinkablePage(currentPageDecorator, getRenderAsLink()));
        }

        return retList;
    }

    public static class LinkablePage {

        private final PageDecorator pageDecorator;
        private final Boolean renderAsLink;

        public LinkablePage(PageDecorator pageDecorator, Boolean renderAsLink) {
            this.pageDecorator = pageDecorator;
            this.renderAsLink = renderAsLink;
        }

        public PageDecorator getPageDecorator() {
            return pageDecorator;
        }

        public Boolean getRenderAsLink() {
            return renderAsLink;
        }

    }
}
