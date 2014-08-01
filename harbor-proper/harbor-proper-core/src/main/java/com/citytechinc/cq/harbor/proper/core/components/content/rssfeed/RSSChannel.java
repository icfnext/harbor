package com.citytechinc.cq.harbor.proper.core.components.content.rssfeed;

import java.util.List;

import com.citytechinc.aem.bedrock.api.link.Link;
import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.link.builders.factory.LinkBuilderFactory;
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
				items.add(getComponent(currentRSSItemComponentNode.getPath(), RSSItem.class).get());
			}
		}

		return items;
	}

}
