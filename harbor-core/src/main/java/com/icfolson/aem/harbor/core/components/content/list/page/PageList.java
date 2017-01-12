package com.icfolson.aem.harbor.core.components.content.list.page;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Page List",
    group = ComponentGroups.HARBOR_LISTS,
    resourceSuperType = AbstractPageList.RESOURCE_TYPE,
    name = "lists/pagelist")
@Model(adaptables = Resource.class)
public class PageList extends AbstractPageList {

    @DialogField(ranking = 1)
    @DialogFieldSet(title = "List Construction")
    @Inject
    @Self
    private PageListConstructionStrategy constructionStrategy;

    @Override
    protected ListConstructionStrategy<PageDecorator> getListConstructionStrategy() {
        return constructionStrategy;
    }
}
