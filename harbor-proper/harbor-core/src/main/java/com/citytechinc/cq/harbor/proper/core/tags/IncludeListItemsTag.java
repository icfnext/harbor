package com.citytechinc.cq.harbor.proper.core.tags;

import com.citytechinc.cq.harbor.proper.api.lists.rendering.RenderableItem;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.api.servlets.ServletResolver;
import org.apache.sling.scripting.jsp.util.JspSlingHttpServletResponseWrapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


/**
 * JSP tag that takes a list of items and renders each using a specified Script.  If no script is specified then we check whether
 * the item is an instance of {@link com.citytechinc.cq.harbor.proper.api.lists.rendering.RenderableItem}.  If it is then we produce a rendering given
 * the implementation in the RenderableItem class.
 */
public class IncludeListItemsTag extends TagSupport {

    private List<?> items;

    private static final String DEFAULT_JSP_VAR_ITEM = "item";
    private String itemVar;

    private String script;

    @Override
    public int doEndTag() throws JspException {

        // check if script path was provided
        if(StringUtils.isNotBlank(this.script)) {

            // path provided, include the script
            this.writeScript();

        } else {

            // script path not provided, just render out each item via its RenderableItem interface
            this.writeListItems();

        }

        return EVAL_PAGE;

    }

    /**
     * Include a rendered JSP page given at path provided.
     *
     * @throws JspException
     */
    private void writeScript() throws JspException {

        /// get request object
        ServletRequest pageContextRequest = this.pageContext.getRequest();

        // get variables necessary to render and pass on jsp
        SlingBindings bindings = (SlingBindings) pageContextRequest.getAttribute(SlingBindings.class.getName());
        ResourceResolver resourceResolver = bindings.getRequest().getResourceResolver();
        SlingScriptHelper scriptHelper = bindings.getSling();
        ServletResolver servletResolver = scriptHelper.getService(ServletResolver.class);

        // get resolved jsp, render it
        Servlet servlet = resolveServletForScript(servletResolver, bindings.getResource(), script);

        if (servlet != null) {

            try {

                // figure out if items var has been set
                String varName = StringUtils.isNotBlank(this.itemVar) ? this.itemVar : DEFAULT_JSP_VAR_ITEM;

                // loop through and render one script per JSP
                for(Object item : this.items) {

                    // put item variable into request
                    pageContextRequest.setAttribute(varName, item);

                    // put rendering in response
                    SlingHttpServletResponse response = new JspSlingHttpServletResponseWrapper(this.pageContext);
                    servlet.service(pageContextRequest, response);

                    // remove item variable from request
                    pageContextRequest.removeAttribute(varName);

                }

            } catch (ServletException e) {

                // servlet error occurred, throw an error
                throw new JspException("Error while executing script " + servlet.getServletInfo(), e);

            } catch (IOException e) {

                // servlet error occurred, throw an error
                throw new JspException("Error while executing script " + servlet.getServletInfo(), e);

            }

        } else {

            // could not find jsp, throw an error
            throw new JspException("Could not find servlet for script " + script);

        }

    }

    private Servlet resolveServletForScript(ServletResolver servletResolver, Resource resourceBeingRendered, String script) {
        return servletResolver.resolveServlet(resourceBeingRendered, script);
    }

    /**
     * Write out list items. This function should only be used if a script path has not been provided.
     */
    private void writeListItems() throws JspException {

        JspWriter out = this.pageContext.getOut();

        // loop through and write out rendered items
        for(Object item : this.items) {

            if (item instanceof RenderableItem) {
                RenderableItem renderableItem = (RenderableItem) item;

                try {

                    out.write(renderableItem.render());

                } catch (IOException e) {

                    throw new JspException("Error while attempting to render list items.", e);

                }
            }


        }

    }

    /**
     * @param items List of items to render.
     */
    public void setItems(List<?> items) {

        this.items = items;

    }

    /**
     * @param itemVar  Name of variable that each item will be accessible through in given JSP. This is optional, and will
     *                      default to 'items' if not provided.
     */
    public void setItemVar(String itemVar) {

        this.itemVar = itemVar;

    }

    /**
     * @param script    Absolute or relative path to JSP to render. Relative paths must be relative to current script
     *                      location. This JSP will be rendered once per item passed in through 'items' attribute.
     */
    public void setScript(String script) {

        this.script = script;

    }

}
