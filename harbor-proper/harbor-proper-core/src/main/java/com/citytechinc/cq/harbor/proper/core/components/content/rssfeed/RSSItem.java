package com.citytechinc.cq.harbor.proper.core.components.content.rssfeed;

import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.link.Link;
import com.citytechinc.cq.library.content.link.builders.LinkBuilder;
import com.citytechinc.cq.library.content.node.ComponentNode;

public class RSSItem extends AbstractComponent {

    public String title;
    public String description;
    public Link link;

    public RSSItem(ComponentNode componentNode) {
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

}
