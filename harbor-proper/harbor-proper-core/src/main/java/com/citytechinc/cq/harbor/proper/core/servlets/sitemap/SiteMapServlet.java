package com.citytechinc.cq.harbor.proper.core.servlets.sitemap;


import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;
import com.citytechinc.cq.harbor.proper.api.services.sitemap.SiteMapService;
import com.citytechinc.cq.library.content.request.ComponentServletRequest;
import com.citytechinc.cq.library.servlets.AbstractComponentServlet;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;

@SlingServlet(
        resourceTypes = "cq:Page",
        selectors = "sitemap",
        methods = "GET",
        extensions = "xml"
)
public class SiteMapServlet extends AbstractComponentServlet {
    protected static final Logger LOG = LoggerFactory.getLogger(SiteMapServlet.class);

    @Reference
    private SiteMapService siteMapService;


    @Override
    protected void processGet(final ComponentServletRequest request) throws ServletException, IOException {
        final SiteMap siteMap = this.siteMapService.getSitemapEntryList(request.getCurrentPage());
        this.writeXmlResponse(request.getSlingResponse(), siteMap);
    }

    protected void writeXmlResponse(final SlingHttpServletResponse slingHttpServletResponse, final SiteMap siteMap) {
        try {
            final JAXBContext context = JAXBContext.newInstance(SiteMap.class);
            final Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(siteMap, slingHttpServletResponse.getOutputStream());
        }catch(final Exception e) {
            final String msg = "Failed to marshal and write siteMap to response";
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

}
