package com.icfolson.aem.harbor.core.components.dynamics.v1;

import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentManager;
import com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem;
import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.servlets.datasource.AbstractOptionsDataSourceServlet;
import com.icfolson.aem.library.core.servlets.optionsprovider.Option;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAllowedDynamicTypesDataSource extends AbstractOptionsDataSourceServlet {

    //TODO: It seems like a waste of processing to calculate this list each time

    @Reference
    private ResourceResolverFactory resourceResolverFactory; //TODO: Use a service resource resolver - in prod a user might not have access to the component definitions

    @Override
    protected List<Option> getOptions(ComponentServletRequest componentServletRequest) {
        return getComponents(componentServletRequest.getResourceResolver()).stream()
                .map(c -> new Option(c.getResourceType(), c.getTitle()))
                .collect(Collectors.toList());
    }

    private List<Component> getComponents(ResourceResolver resourceResolver) {
        return resourceResolver.adaptTo(ComponentManager.class).getComponents().stream()
                .filter( c -> c.adaptTo(Resource.class).isResourceType(getItemResourceType()) )
                .collect(Collectors.toList());
    }

    public abstract String getItemResourceType();

}
