package com.citytechinc.cq.harbor.proper.core.resource.provider.rss;

import com.citytechinc.cq.harbor.proper.api.content.rss.RSSChannel;
import com.citytechinc.cq.harbor.proper.api.content.rss.RSSFeed;
import com.citytechinc.cq.harbor.proper.api.content.rss.RSSItem;
import com.citytechinc.cq.harbor.proper.api.services.rss.RSSFeedGeneratorService;
import com.citytechinc.cq.harbor.proper.api.services.rss.RssResourceProvider;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component(label = "RSS Resource Provider", metatype = true, configurationFactory = true)
@Service
public class DefaultRssResourceProvider implements ModifyingResourceProvider, RssResourceProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRssResourceProvider.class);

    @Property(label = "RSS Feed Title", value = "", description = "A title used to identify this Service Instance externally.  Setting the title has no systematic consequence as far as the feed itself is concerned")
    private static final String TITLE_PROPERTY = "rssFeedTitle";
    private String rssFeedTitle;

    @Property(label = "Resource Provider Root", value = "", description = "The root in the content tree which this service will respond to")
    private static final String PROVIDER_ROOT_PROPERTY = ResourceProvider.ROOTS;
    private String providerRoot;
    private String absoluteProviderRoot;

    //TODO: Determine why making the value blank here instead of 'none' causes the maven-bundle-plugin to error on build
    @Property(label = "RSS Feed URL", value = "none", description = "This is temporary config.  Point it to the URL of the RSS feed")
    private static final String RSS_FEED_URL_PROPERTY = "rssFeedUrl";
    private String rssFeedUrl;

    @Reference
    private RSSFeedGeneratorService rssFeedGeneratorService;


    @Activate
    @Modified
    protected void activate(final Map<String, Object> properties) throws Exception {
        this.providerRoot = PropertiesUtil.toString(properties.get(PROVIDER_ROOT_PROPERTY), "");
        this.absoluteProviderRoot = "/" + this.providerRoot;
        this.rssFeedUrl = PropertiesUtil.toString(properties.get(RSS_FEED_URL_PROPERTY), "none");
        this.rssFeedTitle = PropertiesUtil.toString(properties.get(TITLE_PROPERTY), rssFeedUrl);
    }

    @Deprecated
    @Override
    public Resource getResource(ResourceResolver resourceResolver, HttpServletRequest httpServletRequest, String path) {
        return getResource(resourceResolver, path);
    }

    /**
     * Paths produced for the resource will be the root for the channel or the root followed by a number for a
     * post.  The posts are numbered in the order they are returned from the feed generator service
     * @param resourceResolver
     * @param path
     * @return
     */
    @Override
    public Resource getResource(ResourceResolver resourceResolver, String path) {
        if (StringUtils.isBlank(getRssFeedUrl()) || "none".equals(getRssFeedUrl()) || StringUtils.isBlank(absoluteProviderRoot)) {
            return null;
        }

        Optional<RSSFeed> rssFeedOptional = rssFeedGeneratorService.getRSSFeed(getRssFeedUrl());

        if (rssFeedOptional.isPresent()) {
            if (absoluteProviderRoot.equals(path)) {
                RSSChannelValueMap rssChannelValueMap = new RSSChannelValueMap(rssFeedOptional.get().getChannel());
                return new RSSChannelResource(resourceResolver, path, rssChannelValueMap);
            }
            else {
                if (path.matches(getRssPathRegularExpression())) {
                    Integer requestedRssItem = Integer.valueOf(path.substring(path.lastIndexOf("/")));
                    if (rssFeedOptional.get().getChannel().getItems().size() >= requestedRssItem) {
                        return new RSSItemResource(resourceResolver, path, new RSSItemValueMap(rssFeedOptional.get().getChannel().getItems().get(requestedRssItem)));
                    }
                }
            }
        }

        return null;
    }

    /**
     * The only Resource in an RSS feed which will have children will be the Channel resource.
     * @param resource
     * @return
     */
    @Override
    public Iterator<Resource> listChildren(Resource resource) {
        if (StringUtils.isBlank(getRssFeedUrl()) || StringUtils.isBlank(absoluteProviderRoot)) {
            return null;
        }

        Optional<RSSFeed> rssFeedOptional = rssFeedGeneratorService.getRSSFeed(getRssFeedUrl());

        if (rssFeedOptional.isPresent()) {
            if (absoluteProviderRoot.equals(resource.getPath())) {
                List<Resource> children = Lists.newArrayList();
                for (int i=0; i<rssFeedOptional.get().getChannel().getItems().size(); i++) {
                    RSSItem currentRssFeedItem = rssFeedOptional.get().getChannel().getItems().get(i);

                    children.add(new RSSItemResource(resource.getResourceResolver(), absoluteProviderRoot + "/" + i, new RSSItemValueMap(currentRssFeedItem)));
                }

                return children.iterator();
            }
        }

        return null;
    }

    @Override
    public String getTitle() {
        return rssFeedTitle;
    }

    @Override
    public String getProviderRoot() {
        return providerRoot;
    }

    @Override
    public String getRssFeedUrl() {
        return rssFeedUrl;
    }

    private String getRssPathRegularExpression() {
        return "^" + absoluteProviderRoot + "/(\\d)*$";
    }

    @Override
    public Resource create(ResourceResolver resourceResolver, String s, Map<String, Object> stringObjectMap) throws PersistenceException {
        LOG.debug("Create request for " + s);
        return null;
    }

    @Override
    public void delete(ResourceResolver resourceResolver, String s) throws PersistenceException {
        LOG.debug("Delete request for " + s);
    }

    @Override
    public void revert(ResourceResolver resourceResolver) {
        LOG.debug("Revert request");
    }

    @Override
    public void commit(ResourceResolver resourceResolver) throws PersistenceException {
        LOG.debug("Commit request");
    }

    @Override
    public boolean hasChanges(ResourceResolver resourceResolver) {
        LOG.debug("Has Changes request");
        return false;
    }

    public static class RSSChannelResource extends AbstractResource implements Resource {

        public static final String RESOURCE_TYPE = "harbor/rss/channel";

        private final String path;
        private final ResourceMetadata metadata;
        private final ValueMap valueMap;
        private final ResourceResolver resolver;

        public RSSChannelResource(ResourceResolver resourceResolver, String path, ValueMap valueMap) {
            this.path = path;
            this.valueMap = valueMap;
            this.resolver = resourceResolver;

            metadata = new ResourceMetadata();
            metadata.setResolutionPath(path);
        }

        @Override
        public String getPath() {
            return path;
        }

        @Override
        public String getResourceType() {
            return RESOURCE_TYPE;
        }

        @Override
        public String getResourceSuperType() {
            return null;
        }

        @Override
        public ResourceMetadata getResourceMetadata() {
            return metadata;
        }

        @Override
        public ResourceResolver getResourceResolver() {
            return resolver;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + path;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
            if(type == ValueMap.class) {
                return (AdapterType)valueMap;
            }
            return super.adaptTo(type);
        }

    }

    public static class RSSChannelValueMap extends ValueMapDecorator {

        //TODO: Fill this out
        public RSSChannelValueMap(RSSChannel channel) {
            super(Maps.<String, Object>newHashMap());
            put("title", channel.getTitle());
            put("link", channel.getLink());
            put("description", channel.getDescription());
        }

    }

    public static class RSSItemResource extends AbstractResource implements Resource {

        public static final String RESOURCE_TYPE = "harbor/rss/item";

        private final String path;
        private final ResourceMetadata metadata;
        private final ValueMap valueMap;
        private final ResourceResolver resolver;

        public RSSItemResource(ResourceResolver resourceResolver, String path, ValueMap valueMap) {
            this.path = path;
            this.valueMap = valueMap;
            this.resolver = resourceResolver;

            metadata = new ResourceMetadata();
            metadata.setResolutionPath(path);
        }

        @Override
        public String getPath() {
            return path;
        }

        @Override
        public String getResourceType() {
            return RESOURCE_TYPE;
        }

        @Override
        public String getResourceSuperType() {
            return null;
        }

        @Override
        public ResourceMetadata getResourceMetadata() {
            return metadata;
        }

        @Override
        public ResourceResolver getResourceResolver() {
            return resolver;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + path;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
            if(type == ValueMap.class) {
                return (AdapterType)valueMap;
            }
            return super.adaptTo(type);
        }

    }

    public static class RSSItemValueMap extends ValueMapDecorator {

        //TODO: Fill this out
        public RSSItemValueMap(RSSItem item) {
            super(Maps.<String, Object>newHashMap());
            put("title", item.getTitle());
            put("link", item.getLink());
            put("description", item.getDescription());
        }

    }
}
