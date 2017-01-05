package com.icfolson.aem.harbor.core.components.content.list.page;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.list.AbstractListComponent;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Component(value = "Page List", group = ComponentGroups.HARBOR_LISTS, name = "lists/pagelist")
@Model(adaptables = Resource.class)
public class PageList extends AbstractListComponent<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField
    @DialogFieldSet(title = "List Construction")
    @Inject
    @Self
    private PageListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet(title = "List Rendering")
    @Inject
    @Self
    private LinkablePageRenderingStrategy renderingStrategy;

    @Override
    protected ListConstructionStrategy<PageDecorator> getListConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> getListRenderingStrategy() {
        return renderingStrategy;
    }
}
