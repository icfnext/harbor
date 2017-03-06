package com.icfolson.aem.harbor.core.components.content.rssfeed;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.services.rss.RssResourceProvider;
import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.constants.PathConstants;
import com.icfolson.aem.library.core.servlets.optionsprovider.AbstractOptionsProviderServlet;
import com.icfolson.aem.library.core.servlets.optionsprovider.Option;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import java.util.List;
import java.util.stream.Collectors;

@SlingServlet(resourceTypes = RSSFeed.RESOURCE_TYPE, selectors = "options", methods = "GET",
    extensions = PathConstants.EXTENSION_JSON)
public class RSSChannelOptionsProviderServlet extends AbstractOptionsProviderServlet {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE, policy = ReferencePolicy.DYNAMIC,
        bind = "bindResourceProvider", unbind = "unbindResourceProvider",
        referenceInterface = RssResourceProvider.class)
    private final List<RssResourceProvider> rssResourceProviders = Lists.newArrayList();

    protected void bindResourceProvider(final RssResourceProvider rssResourceProvider) {
        synchronized (rssResourceProviders) {
            if (!rssResourceProviders.contains(rssResourceProvider)) {
                rssResourceProviders.add(rssResourceProvider);
            }
        }
    }

    protected void unbindResourceProvider(final RssResourceProvider rssResourceProvider) {
        synchronized (rssResourceProviders) {
            rssResourceProviders.remove(rssResourceProvider);
        }
    }

    @Override
    protected List<Option> getOptions(final ComponentServletRequest componentServletRequest) {
        return ImmutableList.copyOf(rssResourceProviders)
            .stream()
            .map(provider -> new Option(provider.getProviderRoot(), provider.getTitle()))
            .collect(Collectors.toList());
    }

    @Override
    protected Optional<String> getOptionsRoot(final ComponentServletRequest componentServletRequest) {
        return Optional.absent();
    }
}
