package com.icfolson.aem.harbor.core.components.content.list.page;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.list.AbstractListComponent;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

public abstract class AbstractPageList extends AbstractListComponent<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> {

    @DialogField(ranking = 2)
    @DialogFieldSet(title = "List Rendering")
    @Inject
    @Self
    private LinkablePageRenderingStrategy renderingStrategy;

    @Override
    protected ListRenderingStrategy<PageDecorator, List<LinkablePageRenderingStrategy.LinkablePage>> getListRenderingStrategy() {
        return renderingStrategy;
    }
}
