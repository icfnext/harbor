package com.citytechinc.cq.harbor.tags;

import com.citytechinc.cq.harbor.components.content.list.RenderableListItem;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSP tag that takes a list of RenderableListItems and renders them with given JSP script.
 */
public class IncludeListItemsTag extends TagSupport {

    private static final String JSP_VAR_ITEMS = "items";
    private List<? extends RenderableListItem> items;

    private String script;

    @Override
    public int doEndTag() throws JspException {

        // set up map of variables that will be passed into the script
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(JSP_VAR_ITEMS, this.items);

        // check if script path was provided
        if(StringUtils.isNotBlank(this.script)) {

            // path provided, include the script
            this.writeScript(variables);

        } else {

            // script path not provided, just render out each item via its RenderableListItem interface
            this.writeListItems();

        }

        return EVAL_PAGE;

    }

    /**
     * Include a rendered JSP page given at path provided.
     *
     * @param variables     Map of variables to pass on to the included JSP.
     * @throws JspException
     */
    private void writeScript(Map<String, Object> variables) throws JspException {

        /// get request object
        ServletRequest request = this.pageContext.getRequest();

        // put all variables into the request
        for(Map.Entry<String, Object> entry : variables.entrySet()) {

            request.setAttribute(entry.getKey(), entry.getValue());

        }

        // get variables necessary to render and pass on jsp
        SlingBindings bindings = (SlingBindings) request.getAttribute(SlingBindings.class.getName());
        ResourceResolver resourceResolver = bindings.getRequest().getResourceResolver();
        SlingScriptHelper scriptHelper = bindings.getSling();
        ServletResolver servletResolver = scriptHelper.getService(ServletResolver.class);

        // resolve script path
        String absScriptPath = StringUtils.EMPTY;

        // check if path is absolute or relative
        if (!this.script.startsWith("/")) {

            // this is a relative path, figure out what the absolute path is
            String parentPath = ResourceUtil.getParent(scriptHelper.getScript().getScriptResource().getPath());
            for (String searchPath : resourceResolver.getSearchPath()) {

                if (parentPath.startsWith(searchPath)) {

                    // found properly resolved parent path that is the root of the absolute path to relative resource
                    parentPath = parentPath.substring(searchPath.length());
                    break;

                }

            }

            // set absolute path to be used for script resolution
            absScriptPath = parentPath + "/" + this.script;

        } else {

            // this is already an absolute path, just use it as is
            absScriptPath = this.script;

        }

        // get resolved jsp
        Servlet servlet = servletResolver.resolveServlet(resourceResolver, absScriptPath);
        if (servlet == null) {

            // could not find jsp, throw an error
            throw new JspException("Could not find script " + absScriptPath);

        }

        try {

            // put rendering in response
            SlingHttpServletResponse response = new JspSlingHttpServletResponseWrapper(this.pageContext);
            servlet.service(this.pageContext.getRequest(), response);

            // remove all variables from request
            for(String key : variables.keySet()) {

                request.removeAttribute(key);

            }

        } catch (ServletException e) {

            // servlet error occurred, throw an error
            throw new JspException("Error while executing script " + absScriptPath, e);

        } catch (IOException e) {

            // servlet error occurred, throw an error
            throw new JspException("Error while executing script " + absScriptPath, e);

        }

    }

    /**
     * Write out list items. This function should only be used if a script path has not been provided.
     */
    private void writeListItems() throws JspException {

        JspWriter out = this.pageContext.getOut();

        // loop through and write out rendered items
        for(RenderableListItem item : this.items) {

            try {

                out.write(item.getRenderedItem());

            } catch (IOException e) {

                throw new JspException("Error while attempting to render list items.", e);

            }

        }

    }

    public void setItems(List<? extends RenderableListItem> items) {

        this.items = items;

    }

    public void setScript(String script) {

        this.script = script;

    }

}
