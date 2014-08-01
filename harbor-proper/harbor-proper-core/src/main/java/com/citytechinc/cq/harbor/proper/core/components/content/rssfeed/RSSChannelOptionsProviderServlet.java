package com.citytechinc.cq.harbor.proper.core.components.content.rssfeed;

import java.util.List;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.AbstractOptionsProviderServlet;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.Option;
import com.citytechinc.cq.harbor.proper.api.services.rss.RssResourceProvider;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@SlingServlet(resourceTypes = RSSFeed.RESOURCE_TYPE, selectors = "options", methods = "GET", extensions = "json")
public class RSSChannelOptionsProviderServlet extends AbstractOptionsProviderServlet {

	@Reference(cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE, policy = ReferencePolicy.DYNAMIC, bind = "bindResourceProvider", unbind = "unbindResourceProvider", referenceInterface = RssResourceProvider.class)
	private final List<RssResourceProvider> rssResourceProviders = Lists.newArrayList();

	protected void bindResourceProvider(RssResourceProvider rssResourceProvider) {

		synchronized (rssResourceProviders) {
			if (!rssResourceProviders.contains(rssResourceProvider)) {
				rssResourceProviders.add(rssResourceProvider);
			}
		}

	}

	protected void unbindResourceProvider(RssResourceProvider rssResourceProvider) {

		synchronized (rssResourceProviders) {
			rssResourceProviders.remove(rssResourceProvider);
		}

	}

	@Override
	protected List<Option> getOptions(ComponentServletRequest componentServletRequest) {
		List<Option> optionList = Lists.newArrayList();

		List<RssResourceProvider> localResourceProviders = ImmutableList.copyOf(rssResourceProviders);

		for (RssResourceProvider currentRssResourceProvider : localResourceProviders) {
			optionList.add(new Option(currentRssResourceProvider.getProviderRoot(), currentRssResourceProvider
				.getTitle()));
		}

		return optionList;
	}

	@Override
	protected Optional<String> getOptionsRoot(ComponentServletRequest componentServletRequest) {
		return Optional.absent();
	}

}
