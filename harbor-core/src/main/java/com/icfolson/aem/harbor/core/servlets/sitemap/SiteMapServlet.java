package com.icfolson.aem.harbor.core.servlets.sitemap;

import com.day.cq.wcm.api.NameConstants;
import com.icfolson.aem.harbor.api.domain.sitemap.SiteMap;
import com.icfolson.aem.harbor.api.services.sitemap.SiteMapService;
import com.icfolson.aem.library.api.request.ComponentServletRequest;
import com.icfolson.aem.library.core.servlets.AbstractComponentServlet;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;

@SlingServlet(
    resourceTypes = NameConstants.NT_PAGE,
    selectors = SiteMapServlet.SELECTOR,
    methods = "GET",
    extensions = SiteMapServlet.EXTENSION)
public class SiteMapServlet extends AbstractComponentServlet {

    public static final String SELECTOR = "sitemap";

    public static final String EXTENSION = "xml";

    @Reference
    private SiteMapService siteMapService;

    @Override
    protected void processGet(final ComponentServletRequest request) throws ServletException, IOException {
        final SiteMap siteMap = siteMapService.getSitemap(request.getCurrentPage());
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
        final JAXBContext context = JAXBContext.newInstance(SiteMap.class);
        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(siteMap, slingHttpServletResponse.getOutputStream());
    }
}
