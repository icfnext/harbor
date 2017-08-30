package com.icfolson.aem.harbor.core.components.dynamics.v1;

import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentManager;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.google.common.collect.Sets;
import com.icfolson.aem.harbor.api.components.dynamics.DynamicComponent;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.servlets.datasource.AbstractOptionsDataSourceServlet;
import com.icfolson.aem.library.core.servlets.optionsprovider.Option;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractDynamicItemDataSource extends AbstractOptionsDataSourceServlet {

    //TODO: It seems like a waste of processing to calculate this list each time

    @Reference
    private ResourceResolverFactory resourceResolverFactory; //TODO: Use a service resource resolver - in prod a user might not have access to the component definitions

    @Override
    protected List<Option> getOptions(ComponentServletRequest componentServletRequest) {
        return getComponents(componentServletRequest.getResourceResolver(), getAllowedDynamicComponents(componentServletRequest)).stream()
                .map(c -> new Option(c.getResourceType(), c.getTitle()))
                .collect(Collectors.toList());
    }

    private List<Component> getComponents(ResourceResolver resourceResolver, Set<String> allowedComponents) {
        return resourceResolver.adaptTo(ComponentManager.class).getComponents().stream()
                .filter( c -> c.adaptTo(Resource.class).isResourceType(getItemResourceType()) )
                .filter( c -> allowedComponents.contains(c.getResourceType()))
                .collect(Collectors.toList());
    }

    private Set<String> getAllowedDynamicComponents(ComponentServletRequest request) {
        Resource currentResource = request.getResourceResolver().getResource(request.getSlingRequest().getRequestPathInfo().getSuffix());
        PageDecorator currentPage = request.getPageManager().getContainingPage(request.getSlingRequest().getRequestPathInfo().getSuffix());

        if (currentPage != null && currentResource != null) {
            Design design = request.getResourceResolver().adaptTo(Designer.class).getDesign(currentPage);

            if (design != null) {
                return Sets.newHashSet(design.getStyle(currentResource).get(DynamicComponent.ALLOWED_LIST_ITEMS, new String[0]));
            }
        }

        return Sets.newHashSet();
    }

    public abstract String getItemResourceType();
}
