package com.citytechinc.cq.harbor.proper.core.components.content.tests.rss;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@SlingServlet(resourceTypes = "harbor/rss/channel", methods = "POST")
public class RssChannelPostServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(RssChannelPostServlet.class);

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException
    {
        LOG.debug("Accepting post for RSS Channel");
    }
}
