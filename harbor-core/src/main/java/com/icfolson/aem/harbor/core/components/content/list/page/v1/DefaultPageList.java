package com.icfolson.aem.harbor.core.components.content.list.page.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.harbor.core.components.content.list.automatedlist.v1.AbstractAutomatedList;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.content.page.impl.PagePredicates;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Component(value = "Page List (v1)",
        name = "lists/pagelist/v1/pagelist",
        resourceSuperType = AbstractAutomatedList.RESOURCE_TYPE,
        group = ComponentGroups.HARBOR_LISTS
    )
@Model(adaptables = Resource.class, adapters = ListComponent.class, resourceType = DefaultPageList.RESOURCE_TYPE)
public class DefaultPageList extends AbstractAutomatedList<ListableLink> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/pagelist/v1/pagelist";

    @Inject
    private PageDecorator currentPage;

    @Override
    public Iterable<ListableLink> getItems() {
        return getStartingPage().getChildren(getInclusionPredicate())
                .stream()
                .map(DefaultPageListItem::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getListType() {
        return ListComponent.UNORDERED_LIST_TYPE;
    }

    public Predicate<PageDecorator> getInclusionPredicate() {
        return PagePredicates.ALL_PAGES_PREDICATE;
    }

    public PageDecorator getStartingPage() {
        return currentPage;
    }

}
