package com.icfolson.aem.harbor.core.components.content.rssfeed;

import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RSSChannel extends AbstractComponent {

    private List<RSSItem> items;

    public String getTitle() {
        return get("title", "");
    }

    public String getDescription() {
        return get("description", "");
    }

    @Override
    public Link getLink() {
        return getAsLink("link").or(LinkBuilderFactory.forPath("#").build());
    }

    public List<RSSItem> getItems() {
        if (items == null) {
            items = getComponentNodes()
                .stream()
                .map(componentNode -> componentNode.getResource().adaptTo(RSSItem.class))
                .collect(Collectors.toList());
        }

        return items;
    }

}
