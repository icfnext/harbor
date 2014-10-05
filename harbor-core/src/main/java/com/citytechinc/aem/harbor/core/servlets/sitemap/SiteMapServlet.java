package com.citytechinc.aem.harbor.core.servlets.sitemap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
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

@SlingServlet(resourceTypes = "cq:Page", selectors = "sitemap", methods = "GET", extensions = "xml")
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
		} catch (final Exception e) {
			final String msg = "Failed to marshal and write siteMap to response";
			LOG.error(msg, e);
			throw new RuntimeException(msg, e);
		}
	}

}
