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

    private List<? extends RenderableListItem> items;

    private String script;

    @Override
    public int doEndTag() throws JspException {

        this.includeScript(this.script);

        return EVAL_PAGE;

    }

    /**
     * Include a rendered JSP page given at path provided.
     *
     * @param scriptPath    Path to script.
     * @throws JspException
     */
    private void includeScript(String scriptPath) throws JspException {

        /// get some necessary variables
        ServletRequest request = this.pageContext.getRequest();
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
