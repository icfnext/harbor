package com.icfolson.aem.harbor.core.components.content.projections.pagereference;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.core.components.content.list.page.LinkablePageRenderingStrategy;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Component(value = "Page Reference", name = "projections/pagereference", tabs = {
    @Tab(title = "Reference"),
    @Tab(title = "Presentation")
}, group = ComponentGroups.HARBOR_PROJECTIONS)
@Model(adaptables = Resource.class)
public class PageReference extends AbstractComponent {

    private LinkablePageRenderingStrategy.LinkablePage referencedPage;

    @DialogField(tab = 2)
    @DialogFieldSet
    private LinkablePageRenderingStrategy renderingStrategy;

    @DialogField(fieldLabel = "Page", tab = 1, required = true)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public LinkablePageRenderingStrategy.LinkablePage getPage() {
        if (referencedPage == null) {
            if (renderingStrategy == null) {
                renderingStrategy = getResource().adaptTo(LinkablePageRenderingStrategy.class);
            }

            final Optional<PageDecorator> pageDecoratorOptional = getAsPage("page");

            if (pageDecoratorOptional.isPresent()) {
                final List<LinkablePageRenderingStrategy.LinkablePage> linkablePageList = renderingStrategy
                    .toRenderableList(Lists.newArrayList(pageDecoratorOptional.get()));

                if (linkablePageList.size() > 0) {
                    referencedPage = linkablePageList.get(0);
                }
            }
        }

        return referencedPage;
    }
}
