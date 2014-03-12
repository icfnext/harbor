package com.citytechinc.cq.harbor.servlets

import com.citytechinc.cq.library.content.request.ComponentServletRequest
import com.citytechinc.cq.library.servlets.AbstractComponentServlet
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletResponse

import javax.servlet.ServletException
import java.text.DateFormat
import java.text.SimpleDateFormat

@SlingServlet(paths = "/bin/rssFeed")
public class RssFeedGeneratorServlet extends AbstractComponentServlet {

	public static final String ITEM_NODE_NAME = "item";
	public static final String RSS_FEED_PUBDATE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

	@Override
	protected final void processGet(final ComponentServletRequest request) throws ServletException, IOException {
		String rssFeedPathsAsString = request.getRequestParameter("rssFeedPaths").or("");
		def requestFeedPaths = new JsonSlurper().parseText(rssFeedPathsAsString);
		def itemList = [];
		requestFeedPaths.each { url ->
			def rootNode = new XmlParser().parse(url);
			rootNode.children().each { channel ->
				channel.children().each { node ->
					if (node.name() == ITEM_NODE_NAME) {
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

		def byPubDateComparator = [
		        compare: { map1, map2 ->
			        def dateFormat = new SimpleDateFormat(RSS_FEED_PUBDATE_DATE_FORMAT);
			        def date1 = dateFormat.parse(map1.pubDate);
			        def date2 = dateFormat.parse(map2.pubDate);
			        return date1.compareTo(date2);
		        }
		] as Comparator;

		itemList = itemList.sort(byPubDateComparator).reverse();

		final SlingHttpServletResponse slingResponse = request.getSlingResponse();
		writeJsonResponse(slingResponse, new JsonBuilder(itemList));
	}
}
