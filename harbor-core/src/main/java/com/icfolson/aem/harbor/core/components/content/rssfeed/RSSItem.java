package com.icfolson.aem.harbor.core.components.content.rssfeed;

import com.icfolson.aem.library.api.link.Link;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class RSSItem extends AbstractComponent {

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
}
