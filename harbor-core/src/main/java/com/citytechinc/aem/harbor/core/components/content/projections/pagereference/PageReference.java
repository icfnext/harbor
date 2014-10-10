package com.citytechinc.aem.harbor.core.components.content.projections.pagereference;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.components.content.list.page.LinkablePageRenderingStrategy;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

@Component(
        value = "Page Reference",
        name = "projections/pagereference",
        tabs = {@Tab(title = "Reference"), @Tab(title = "Presentation"), },
        group = ComponentGroups.HARBOR_PROJECTIONS
)
@AutoInstantiate(instanceName = "pagereference")
public class PageReference extends AbstractComponent{

    private LinkablePageRenderingStrategy.LinkablePage referencedPage;

    @DialogField(tab = 2)
    @DialogFieldSet
    private LinkablePageRenderingStrategy renderingStrategy;

    @DialogField(fieldLabel = "Page", tab = 1)
    @PathField
    public LinkablePageRenderingStrategy.LinkablePage getPage() {
        if (referencedPage == null) {
            if (renderingStrategy == null) {
                renderingStrategy = new LinkablePageRenderingStrategy(this);
            }

            Optional<PageDecorator> pageDecoratorOptional = getAsPage("page");

            if (pageDecoratorOptional.isPresent()) {
                List<LinkablePageRenderingStrategy.LinkablePage> linkablePageList =
                        renderingStrategy.toRenderableList(Lists.newArrayList(pageDecoratorOptional.get()));

                if (linkablePageList.size() > 0) {
                    referencedPage = linkablePageList.get(0);
                }
            }
        }

        return referencedPage;
    }
}
