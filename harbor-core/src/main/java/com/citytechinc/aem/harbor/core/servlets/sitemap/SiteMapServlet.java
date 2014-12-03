package com.citytechinc.aem.harbor.core.servlets.sitemap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.AbstractComponentServlet;
import com.citytechinc.aem.harbor.api.domain.sitemap.SiteMap;
import com.citytechinc.aem.harbor.api.services.sitemap.SiteMapService;

@SlingServlet(
        resourceTypes = SiteMapServlet.RESOURCE_TYPE,
        selectors = SiteMapServlet.SELECTOR,
        methods = "GET",
        extensions = SiteMapServlet.EXTENSION)
public class SiteMapServlet extends AbstractComponentServlet {

	private static final Logger LOG = LoggerFactory.getLogger(SiteMapServlet.class);

    public static final String SELECTOR = "sitemap";
    public static final String EXTENSION = "xml";
    public static final String RESOURCE_TYPE = "cq:Page";

	@Reference
	private SiteMapService siteMapService;

	@Override
	protected void processGet(final ComponentServletRequest request) throws ServletException, IOException {
		final SiteMap siteMap = this.siteMapService.getSitemap(request.getCurrentPage());
        try {
            request.getSlingResponse().setContentType("application/xml");
            request.getSlingResponse().setCharacterEncoding("UTF-8");
            this.writeXmlResponse(request.getSlingResponse(), siteMap);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

	protected void writeXmlResponse(final SlingHttpServletResponse slingHttpServletResponse, final SiteMap siteMap) throws JAXBException, IOException {

			final JAXBContext context = JAXBContext.newInstance(SiteMap.class);
			final Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(siteMap, slingHttpServletResponse.getOutputStream());

	}

}
