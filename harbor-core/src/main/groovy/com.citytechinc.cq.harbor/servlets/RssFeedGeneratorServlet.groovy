package com.citytechinc.cq.harbor.servlets

import com.citytechinc.cq.library.servlets.AbstractJsonResponseServlet
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.ServletException

@SlingServlet(paths = "bin/rssFeed")
public class RssFeedGeneratorServlet extends AbstractJsonResponseServlet {


	@Override
	protected final void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
	throws ServletException, IOException {
		def url = "http://news.google.com/news?ned=us&topic=h&output=rss";
		def rss = new XmlSlurper().parse(url);
		writeJsonResponse(response, rss);
	}
}