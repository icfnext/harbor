package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;

public class RSSFeedRenderingStrategy implements ListRenderingStrategy<RSSFeedItem> {

    @DialogField(fieldLabel = "Render as Link?")
    @Selection(type = Selection.CHECKBOX, options = {@Option(text = "", value = "true")})
    private final Boolean renderAsLink;

    public RSSFeedRenderingStrategy(ComponentNode componentNode) {
        renderAsLink = componentNode.get("renderAsLink", false);
    }

    @Override
    public String renderListItem(RSSFeedItem item) {
        StringBuffer renderingStringBuffer = new StringBuffer();

        renderingStringBuffer.append("<div>");
        renderingStringBuffer.append(String.format("Item title: %s", item.getTitle()));
        renderingStringBuffer.append("<br/>");
        renderingStringBuffer.append(String.format("Item Link: %s", item.getLink()));
        renderingStringBuffer.append("<br/>");
        renderingStringBuffer.append(String.format("Item description: %s", item.getDescription()));
        renderingStringBuffer.append("<br/>");
        renderingStringBuffer.append(String.format("Item PubDate: %s", item.getPubDate()));
        renderingStringBuffer.append("</div>");

        return renderingStringBuffer.toString();
    }

}
