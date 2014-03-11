package com.citytechinc.cq.harbor.servlets

import com.citytechinc.cq.library.content.request.ComponentServletRequest
import com.citytechinc.cq.library.servlets.AbstractComponentServlet
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletResponse

import javax.servlet.ServletException

@SlingServlet(paths = "/bin/rssFeed")
public class RssFeedGeneratorServlet extends AbstractComponentServlet {

	@Override
	protected final void processGet(final ComponentServletRequest request) throws ServletException, IOException {
		String rssFeedPathsAsString = request.getRequestParameter("rssFeedPaths").or("");
		def requestFeedPaths = new JsonSlurper().parseText(rssFeedPathsAsString);
		def itemList = new ArrayList();
		requestFeedPaths.each { url ->
			def rootNode = new XmlParser().parse(url);
			rootNode.children().each { channel ->
				channel.children().each { node ->
					//TODO: make this a constant
					if (node.name() == "item") {
						def itemMap = [
								title: node.title.text(),
								link: node.link.text(),
								pubDate: node.pubDate.text(),
								description: node.description.text()
						]
						itemList.add(itemMap);
					}
				}
			}
		}

		final SlingHttpServletResponse slingResponse = request.getSlingResponse();
		writeJsonResponse(slingResponse, new JsonBuilder(itemList));
	}
}
