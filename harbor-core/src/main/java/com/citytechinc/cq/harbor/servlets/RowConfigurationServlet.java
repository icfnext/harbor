package com.citytechinc.cq.harbor.servlets;

import com.citytechinc.cq.library.content.request.ComponentServletRequest;
import com.citytechinc.cq.library.servlets.AbstractComponentServlet;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

@SlingServlet(resourceTypes = "sling/servlet/default",
    selectors="rowConfiguration",
    extensions="json",
    methods="GET")
public class RowConfigurationServlet extends AbstractComponentServlet{

    @Override
    protected final void processGet(final ComponentServletRequest request){
        final SlingHttpServletResponse slingResponse = request.getSlingResponse();
        //Grab the row identifier from the request. Something like: "/content/dasd/jcr:content/mainpar/columnrow"
        //find the component at that the identifier's location

        //build an object containing column information inside the row (list of column information objects)

        //write response to our response object
        //writeJsonResponse
    }
}
