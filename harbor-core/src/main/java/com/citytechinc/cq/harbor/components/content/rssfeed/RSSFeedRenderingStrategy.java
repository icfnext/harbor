package com.citytechinc.cq.harbor.components.content.rssfeed;

import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;

public class RSSFeedRenderingStrategy implements ListRenderingStrategy<RSSFeedItem> {

    private static final String ITEM_TITLE_HTML = "<a href=\"%s\">%s</a><small>%s</small><br>%s";

    @Override
    public String renderListItem(RSSFeedItem item) {
        StringBuffer renderingStringBuffer = new StringBuffer();
        renderingStringBuffer.append(String.format(ITEM_TITLE_HTML, item.getLink(), item.getTitle(), item.getPubDate(), item.getDescription()));
        return renderingStringBuffer.toString();
    }

}
