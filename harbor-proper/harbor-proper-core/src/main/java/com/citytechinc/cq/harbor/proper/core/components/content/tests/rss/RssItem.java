package com.citytechinc.cq.harbor.proper.core.components.content.tests.rss;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;

/**
 * TODO: Remove
 */
@Component(value = "RSS Item", group = ".hidden")
@AutoInstantiate(instanceName = "rssitem")
public class RssItem extends AbstractComponent {

    public RssItem(ComponentRequest request) {
        super(request);
    }

    @DialogField(fieldLabel = "Title")
    public String getTitle() {
        return get("title", "");
    }

}
