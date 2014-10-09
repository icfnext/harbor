package com.citytechinc.aem.harbor.core.components.content.list.link;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.HtmlTag;
import com.citytechinc.cq.component.annotations.widgets.PathField;

@Component(
        value = "Listable Link",
        group = ".hidden",
        name = "lists/linklist/listablelink",
        htmlTag = {@HtmlTag(tagName="li")},
        layout = "rollover"
)
@AutoInstantiate(instanceName = "listablelink")
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
