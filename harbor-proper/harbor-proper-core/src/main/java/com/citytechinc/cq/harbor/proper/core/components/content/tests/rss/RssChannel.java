package com.citytechinc.cq.harbor.proper.core.components.content.tests.rss;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component(value = "RSS Channel", group = ".hidden", resourceSuperType = "harbor/rss/channel")
@AutoInstantiate(instanceName = "rssChannel")
public class RssChannel extends AbstractComponent {

    public RssChannel(ComponentRequest request) {
        super(request);
    }

    @DialogField(fieldLabel = "Title")
    public String getTitle() {
        return get("title", "");
    }

}
