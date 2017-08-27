package com.icfolson.aem.harbor.core.components.content.breadcrumb.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.components.content.breadcrumb.*;
import com.icfolson.aem.harbor.api.content.page.HierarchicalPage;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Component(value = "Breadcrumb (v1)",
    group = ComponentGroups.HARBOR_NAVIGATION,
    name = "breadcrumb/v1/breadcrumb",
    tabs = { @Tab(title = "Breadcrumb (v1)", touchUINodeName = "breadcrumb") })
@Model(adaptables = Resource.class, adapters = Breadcrumb.class, resourceType = DefaultBreadcrumb.RESOURCE_TYPE)
public class DefaultBreadcrumb implements Breadcrumb<BreadcrumbItem> {

    public static final String RESOURCE_TYPE = "harbor/components/content/breadcrumb/v1/breadcrumb";

    @Inject
    private PageDecorator currentPage;

    private Optional<PageDecorator> rootPageOptional;
    private List<BreadcrumbItem> trail;

    @Override
    public Iterable<BreadcrumbItem> getTrail() {
        if (trail != null) {
            return trail;
        }

        trail = Lists.newArrayList();
        Optional<PageDecorator> rootPageOptional = getRootPage();

        if (!rootPageOptional.isPresent()) {
            return trail;
        }

        if (includeCurrentPage()) {
            trail.add(new DefaultBreadcrumbItem(currentPage,
                    currentPage.getPath().equals(rootPageOptional.get().getPath()),
                    true));
        }

        PageDecorator currentPagePointer = currentPage.getParent();

        while(currentPagePointer != null && !currentPagePointer.getPath().equals(rootPageOptional.get().getPath())) {
            if (!currentPagePointer.isHideInNav()) {
                trail.add(new DefaultBreadcrumbItem(currentPagePointer));
            }

            currentPagePointer = currentPagePointer.getParent();
        }

        if (includeRootPage() && !currentPage.getPath().equals(rootPageOptional.get().getPath())) {
            trail.add(new DefaultBreadcrumbItem(currentPagePointer, true, false));
        }

        trail = Lists.reverse(trail);

        return trail;
    }

    @Override
    public boolean includeCurrentPage() {
        return true;
    }

    @Override
    public boolean includeRootPage() {
        return true;
    }

    protected Optional<PageDecorator> getRootPage() {
        if (this.rootPageOptional == null) {
            HierarchicalPage hierarchicalPage = currentPage.adaptTo(HierarchicalPage.class);

            if (hierarchicalPage.getSectionLandingPage().isPresent()) {
                this.rootPageOptional = Optional.of(hierarchicalPage.getSectionLandingPage().get());
            }
            else if (hierarchicalPage.getHomePage().isPresent()) {
                this.rootPageOptional = Optional.of(hierarchicalPage.getHomePage().get());
            }
            else {
                this.rootPageOptional = Optional.empty();
            }

        }

        return rootPageOptional;
    }

}
