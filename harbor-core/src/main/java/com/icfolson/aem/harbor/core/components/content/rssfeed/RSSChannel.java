package com.icfolson.aem.harbor.core.components.content.rssfeed;

import java.util.List;

import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;
import com.google.common.collect.Lists;

public class RSSChannel extends AbstractComponent {

	private String title;
	private String description;
	private Link link;
	private List<RSSItem> items;

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

	@Override
	public Link getLink() {
		if (link == null) {
			link = getAsLink("link").or(LinkBuilderFactory.forPath("#").build());
		}

		return link;
	}

	public List<RSSItem> getItems() {
		if (items == null) {
			items = Lists.newArrayList();

			for (ComponentNode currentRSSItemComponentNode : getComponentNodes()) {
				items.add(currentRSSItemComponentNode.getResource().adaptTo(RSSItem.class));
			}
		}

		return items;
	}

}
