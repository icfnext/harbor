package com.citytechinc.cq.harbor.proper.core.components.content.rssfeed;

import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.link.Link;
import com.citytechinc.cq.library.content.link.builders.LinkBuilder;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.google.common.collect.Lists;

import java.util.List;

public class RSSChannel extends AbstractComponent {

    private String title;
    private String description;
    private Link link;
    private List<RSSItem> items;

    public RSSChannel(ComponentNode componentNode) {
        super(componentNode);
    }

    public String getTitle() {
        if (title == null) {
            title = get("title", "");
        }

        return title;
    }

    public String getDescription() {
        if (description == null) {
            description = get("description", "");
        }

        return description;
    }

    public Link getLink() {
        if (link == null) {
            link = getAsLink("link").or(LinkBuilder.forPath("#").build());
        }

        return link;
    }

    public List<RSSItem> getItems() {
        if (items == null) {
            items = Lists.newArrayList();

            for (ComponentNode currentRSSItemComponentNode : getComponentNodes()) {
                items.add(new RSSItem(currentRSSItemComponentNode));
            }
        }

        return items;
    }

}
