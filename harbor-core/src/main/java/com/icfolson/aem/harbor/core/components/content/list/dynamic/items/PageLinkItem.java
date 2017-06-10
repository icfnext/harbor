package com.icfolson.aem.harbor.core.components.content.list.dynamic.items;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Component(
        value = "Page Link Item",
        group = ".hidden",
        resourceSuperType = DynamicListItem.RESOURCE_TYPE,
        name = "lists/dynamiclist/items/pagelinkitem",
        listeners = {
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
                @Listener(name = "afterdelete", value = "REFRESH_PARENT")
        })
@Model(adaptables = Resource.class)
public class PageLinkItem {

    @DialogField(fieldLabel = "Linked Page") @PathField
    @Inject @Optional
    private PageDecorator linkedPage;

    @DialogField(fieldLabel = "Label") @TextField
    @Inject @Optional
    private String label;

    public String getUrl() {
        return linkedPage.getHref();
    }

    public String getLabel() {
        if (StringUtils.isNotBlank(label)) {
            return label;
        }

        if (linkedPage != null) {
            if (StringUtils.isNotBlank(linkedPage.getPageTitle())) {
                return linkedPage.getPageTitle();
            }

            return linkedPage.getPageTitle();
        }

        return StringUtils.EMPTY;
    }

}
