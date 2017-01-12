package com.icfolson.aem.harbor.core.filters.component.impl;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.components.IncludeOptions;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.namespace.api.ontology.Properties;
import org.apache.sling.api.SlingHttpServletRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Adds classifications identified by the ct:classification property to the wrapper dom as classes.  Classifications
 * are expected to be Tag identifiers.  The identified tag's names are added as classes to the DOM element.
 * <p>
 * TODO: Consider for removal - for now I am commenting this out under the presumption that it will be removed
 */
//@SlingFilter(order = 100, scope = SlingFilterScope.COMPONENT)
public final class ComponentClassificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        IncludeOptions includeOptions = IncludeOptions.getOptions(request, true);

        SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;

        List<String> classifications = slingRequest.getResource().adaptTo(ComponentNode.class).getAsList(
            Properties.ICF_OLSON_CLASSIFICATION, String.class);

        if (classifications.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        TagManager tagManager = slingRequest.getResource().getResourceResolver().adaptTo(TagManager.class);

        for (String currentClassificationId : classifications) {
            Tag currentClassificationTag = tagManager.resolve(currentClassificationId);

            if (currentClassificationTag != null) {
                includeOptions.getCssClassNames().add(currentClassificationTag.getName());
            }
        }

        request.setAttribute(IncludeOptions.ATTR_NAME, includeOptions);
        filterChain.doFilter(request, response);

        /*
        ComponentContext componentContext = (ComponentContext) request.getAttribute(ComponentContext.CONTEXT_ATTR_NAME);

        if (componentContext == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;

        List<String> classifications = slingRequest.getResource().adaptTo(ComponentNode.class).getAsList(Properties.CITYTECH_CLASSIFICATION, String.class);

        if (classifications.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        TagManager tagManager = slingRequest.getResource().getResourceResolver().adaptTo(TagManager.class);

        for (String currentClassificationId : classifications) {
            Tag currentClassificationTag = tagManager.resolve(currentClassificationId);

            if (currentClassificationTag != null) {
                componentContext.getCssClassNames().add(currentClassificationTag.getName());
            }
        }

        request.setAttribute(ComponentContext.CONTEXT_ATTR_NAME, componentContext);
        filterChain.doFilter(request, response);
        */
    }

    @Override
    public void destroy() {

    }
}
