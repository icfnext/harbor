package com.citytechinc.cq.harbor.proper.core.components.page.master.listitem;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;

@Component( value = "Page List Item", group = ".hidden", noDecoration = true, editConfig = false, path = "page/common/master/listitem" )
public class PageListItem extends AbstractComponent {

    private final PageDecorator page;

    public PageListItem(ComponentRequest request) {
        super(request);

        PageManagerDecorator pageManagerDecorator = request.getResourceResolver().adaptTo(PageManagerDecorator.class);

        page = pageManagerDecorator.getContainingPage(request.getResource());
    }

    public PageDecorator getPage() {
        return page;
    }

    public String getListItemTitle() {
        if (getPage().getPageTitleOptional().isPresent()) {
            return getPage().getPageTitleOptional().get();
        }

        return getPage().getTitle();
    }

}
