package com.icfolson.aem.harbor.core.components.content.rssfeed;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Optional;
import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component("RSS Feed")
@AutoInstantiate(instanceName = "rssFeed")
@Model(adaptables = Resource.class)
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
                    rssChannelOptional = Optional.fromNullable(rssChannelResource.adaptTo(RSSChannel.class));
                }
            }
        }

        return rssChannelOptional;
    }
}
