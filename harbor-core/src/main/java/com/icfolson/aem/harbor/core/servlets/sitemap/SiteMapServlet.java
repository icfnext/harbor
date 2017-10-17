package com.icfolson.aem.harbor.core.servlets.sitemap;

import com.day.cq.wcm.api.NameConstants;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMap;
import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.servlets.AbstractComponentServlet;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@SlingServlet(
    resourceTypes = NameConstants.NT_PAGE,
    selectors = SiteMapServlet.SELECTOR,
    methods = "GET",
    extensions = SiteMapServlet.EXTENSION)
public class SiteMapServlet extends AbstractComponentServlet {

    public static final String SELECTOR = "sitemap";

    public static final String EXTENSION = "xml";

    @Override
    protected void processGet(final ComponentServletRequest request) throws ServletException, IOException {
        final SiteMap siteMap = request.getSlingRequest().adaptTo(SiteMap.class);
        final SlingHttpServletResponse slingResponse = request.getSlingResponse();

        try {
            slingResponse.setContentType("application/xml");
            slingResponse.setCharacterEncoding("UTF-8");

            writeXmlResponse(slingResponse, siteMap);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    protected void writeXmlResponse(final SlingHttpServletResponse slingHttpServletResponse, final SiteMap siteMap)
        throws JAXBException, IOException {
        siteMap.marshall(slingHttpServletResponse.getOutputStream());
    }
}
