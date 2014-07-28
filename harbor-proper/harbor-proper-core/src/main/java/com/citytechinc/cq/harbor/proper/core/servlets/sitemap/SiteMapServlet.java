package com.citytechinc.cq.harbor.proper.core.servlets.sitemap;


import com.citytechinc.cq.harbor.proper.api.domain.sitemap.SiteMap;
import com.citytechinc.cq.harbor.proper.api.services.sitemap.SiteMapService;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.day.cq.wcm.api.Page;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
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
public class SiteMapServlet extends SlingAllMethodsServlet {
    protected static final Logger LOG = LoggerFactory.getLogger(SiteMapServlet.class);

    @Reference
    private SiteMapService siteMapService;


    @Override
    protected void doGet(final SlingHttpServletRequest slingHttpServletRequest, final SlingHttpServletResponse slingHttpServletResponse) throws ServletException, IOException {
        final PageDecorator currentPage = this.unpackCurrentPage(slingHttpServletRequest);
        final SiteMap siteMap = this.siteMapService.getSitemapEntryList(currentPage);

        this.writeXmlResponse(slingHttpServletResponse, siteMap);
    }

    protected PageDecorator unpackCurrentPage(final SlingHttpServletRequest slingHttpServletRequest) {
        final ResourceResolver resourceResolver = slingHttpServletRequest.getResourceResolver();
        final PageManagerDecorator pageManager = resourceResolver.adaptTo(PageManagerDecorator.class);
        final Page page = pageManager.getContainingPage(slingHttpServletRequest.getResource());

        return pageManager.getPage(page);
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
