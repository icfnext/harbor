package com.citytechinc.cq.harbor.components.content.list.demo;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class LinkablePageRenderingStrategy implements ListRenderingStrategy<PageDecorator> {

    @DialogField( fieldLabel = "Render as Link?" )
    @Selection( type = Selection.CHECKBOX, options = { @Option( text = "", value = "true" ) } )
    private final Boolean renderAsLink;

    public LinkablePageRenderingStrategy(ComponentNode componentNode) {
        renderAsLink = componentNode.get("renderAsLink", false);
    }

    @Override
    public String renderListItem(PageDecorator item) {
        StringBuffer renderingStringBuffer = new StringBuffer();

        if (renderAsLink) {
            renderingStringBuffer.append("<a href=\"" + item.getHref() + "\">");
        }

        if (item.getPageTitleOptional().isPresent()) {
            renderingStringBuffer.append(item.getPageTitle());
        }
        else {
            renderingStringBuffer.append(item.getTitle());
        }

        if (renderAsLink) {
            renderingStringBuffer.append("</a>");
        }

        return renderingStringBuffer.toString();
    }

}
