package com.citytechinc.aem.harbor.core.components.content.rssfeed;

import com.citytechinc.aem.bedrock.api.link.Link;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.link.builders.factory.LinkBuilderFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class RSSItem extends AbstractComponent {

	public String title;
	public String description;
	public Link link;

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

}
