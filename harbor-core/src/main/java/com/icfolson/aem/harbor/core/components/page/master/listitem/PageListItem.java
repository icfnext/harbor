package com.icfolson.aem.harbor.core.components.page.master.listitem;

import com.citytechinc.cq.component.annotations.Component;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.enums.TitleType;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(value = "Page List Item",
    group = ComponentConstants.GROUP_HIDDEN,
    noDecoration = true,
    editConfig = false,
    path = "page/common/master/listitem")
@Model(adaptables = Resource.class)
public class PageListItem {

    @Inject
    private PageDecorator currentPage;

    public String getListItemTitle() {
        return currentPage.getTitle(TitleType.PAGE_TITLE).or(currentPage.getTitle());
    }
}
