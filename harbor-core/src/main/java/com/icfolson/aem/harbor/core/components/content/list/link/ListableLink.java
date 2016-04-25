package com.icfolson.aem.harbor.core.components.content.list.link;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
        value = "Listable Link",
        group = ".hidden",
        name = "lists/linklist/listablelink",
        htmlTag = {@HtmlTag(tagName="li")},
        layout = "rollover"
)
@AutoInstantiate(instanceName = "listablelink")
@Model(adaptables = Resource.class)
public class ListableLink extends AbstractComponent {

    @DialogField(fieldLabel = "Path")
    @PathField
    public String getHref() {
        return getAsHref("href").or("#");
    }

    @DialogField(fieldLabel = "Title")
    public String getTitle() {
        return IconUtils.iconify(get("title", "Link Title"));
    }
}
