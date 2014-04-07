package com.citytechinc.cq.harbor.tags;

import com.citytechinc.cq.harbor.components.content.list.RenderableListItem;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.api.servlets.ServletResolver;
import org.apache.sling.scripting.jsp.util.JspSlingHttpServletResponseWrapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * JSP tag that takes a list of RenderableListItems and renders them with given JSP script.
 */
public class IncludeListItemsTag extends TagSupport {

    private static final String JSP_VAR_ITEMS = "items";
    private List<? extends RenderableListItem> items;

    private String script;

    @Override
    public int doEndTag() throws JspException {

        String resolvedPath = resolveScriptPath();
        this.includeScript(resolvedPath);

        return EVAL_PAGE;

    }

    private String resolveScriptPath() {

// TODO : have method to figure out script path relative to current script
/*
includeScript is copied from com.day.cq.wcm:cq-wcm-taglib bundle, use that to figure relative stuff
 */

        return this.script;

    }

    /**
     * Include a rendered JSP page given at path provided.
     *
     * @param scriptPath    Path to script.
     * @throws JspException
     */
    private void includeScript(String scriptPath) throws JspException {

        /// get request and set the items variable on it
        ServletRequest request = this.pageContext.getRequest();
        request.setAttribute(JSP_VAR_ITEMS, this.items);

        // get variables necessary to render and pass on jsp
        SlingBindings bindings = (SlingBindings) request.getAttribute(SlingBindings.class.getName());
        ResourceResolver resourceResolver = bindings.getRequest().getResourceResolver();
        SlingScriptHelper scriptHelper = bindings.getSling();
        ServletResolver servletResolver = scriptHelper.getService(ServletResolver.class);

        // get resolved jsp
        Servlet servlet = servletResolver.resolveServlet(resourceResolver, scriptPath);
        if (servlet == null) {

            // could not find jsp, throw an error
            throw new JspException("Could not find script " + scriptPath);

        }

        try {

            // put rendering in response
            SlingHttpServletResponse response = new JspSlingHttpServletResponseWrapper(this.pageContext);
            servlet.service(this.pageContext.getRequest(), response);

            // remove items variable from request variables
            request.removeAttribute(JSP_VAR_ITEMS);

        } catch (ServletException e) {

            // servlet error occurred, throw an error
            throw new JspException("Error while executing script " + scriptPath, e);

        } catch (IOException e) {

            // servlet error occurred, throw an error
            throw new JspException("Error while executing script " + scriptPath, e);

        }

    }

    public void setItems(List<? extends RenderableListItem> items) {

        this.items = items;

    }

    public void setScript(String script) {

        this.script = script;

    }

}
