package com.citytechinc.cq.harbor.proper.core.components.content.rssfeed;

import org.apache.sling.api.resource.Resource;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Optional;

@Component("RSS Feed")
@AutoInstantiate(instanceName = "rssFeed")
public class RSSFeed extends AbstractComponent {

	public static final String RESOURCE_TYPE = "harbor/components/content/rssfeed";

	private Optional<RSSChannel> rssChannelOptional;

	@DialogField(fieldLabel = "RSS Channel")
	@Selection(type = Selection.SELECT, optionsUrl = "$PATH.options.json")
	public RSSChannel getRssChannel() {
		return rssChannelOptional.orNull();
	}

	public boolean getHasRssChannel() {
		return getRssChannelOptional().isPresent();
	}

	public Optional<RSSChannel> getRssChannelOptional() {
		if (rssChannelOptional == null) {
			rssChannelOptional = Optional.absent();

			Optional<String> rssChannelPathOptional = get("rssChannel", String.class);
			if (rssChannelPathOptional.isPresent()) {
				Resource rssChannelResource = getResource().getResourceResolver().getResource(
					"/" + rssChannelPathOptional.get());

				if (rssChannelResource != null) {
					rssChannelOptional = getComponent(rssChannelResource.getPath(), RSSChannel.class);
				}
			}

		}

		return rssChannelOptional;
	}

}
