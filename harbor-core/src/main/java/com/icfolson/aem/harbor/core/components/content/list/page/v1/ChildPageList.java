package com.icfolson.aem.harbor.core.components.content.list.page.v1;

import com.google.common.base.Predicate;
import com.icfolson.aem.harbor.api.components.content.list.ListComponent;
import com.icfolson.aem.harbor.api.components.content.list.linklist.ListableLink;
import com.icfolson.aem.harbor.core.components.content.list.automatedlist.v1.AbstractAutomatedList;
import com.icfolson.aem.harbor.core.content.page.v1.PagePredicates;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, adapters = ListComponent.class, resourceType = ChildPageList.RESOURCE_TYPE)
public class ChildPageList extends AbstractAutomatedList<ListableLink> {

    public static final String RESOURCE_TYPE = "harbor/components/content/lists/childpagelist/v1/childpagelist";

    @Inject
    private PageDecorator currentPage;

    @Override
    public Iterable<ListableLink> getItems() {
        return getPageItems()
                .stream()
                .map(this::transformPageItem)
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

    public List<PageDecorator> getPageItems() {
        return getStartingPage().getChildren(getInclusionPredicate());
    }

    public ListableLink transformPageItem(PageDecorator page) {
        return new DefaultPageListItem(page);
    }

}
