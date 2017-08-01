package com.icfolson.aem.harbor.core.components.design.container;


import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.icfolson.aem.harbor.api.components.design.container.ContainerDesign;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.PageManagerDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = ContainerDesign.class, resourceType = "wcm/foundation/components/page")
public class PageContainerDesign implements ContainerDesign {

    @Inject
    private Resource resource;

    @Override
    public boolean isUsesResponsiveGrid() {

        final ResourceResolver resourceResolver = resource.getResourceResolver();
        final PageDecorator currentPage = resourceResolver.adaptTo(PageManagerDecorator.class).getContainingPage(resource);

        if (currentPage != null) {

            Design design = resourceResolver.adaptTo(Designer.class).getDesign(currentPage);

            if (design != null) {
                return design.getContentResource().getValueMap().get(ContainerDesign.USES_RESPONSIVE_GRID, false);
            }

        }

        return false;
    }

}
