package com.citytechinc.cq.harbor.services.impl

import com.citytechinc.cq.harbor.components.content.rssfeed.RSSFeedItem
import com.citytechinc.cq.harbor.services.RSSFeedGeneratorService
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Deactivate
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.resource.LoginException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xml.sax.SAXException

import java.text.SimpleDateFormat

@Service
@Component(label = "RSS Feed Generator Service", immediate = true, metatype = true)
public final class DefaultRSSFeedGeneratorService implements RSSFeedGeneratorService {

	Logger LOG = LoggerFactory.getLogger(DefaultRSSFeedGeneratorService.class);

	private static final String ITEM_NODE_NAME = "item";
	public static final String RSS_FEED_PUBDATE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

	@Override
	public final List<RSSFeedItem> getListOfRSSFeedItemsFromUrls(final List<String> rssPaths, final int numberOfItemsToDisplay) {
		List<RSSFeedItem> itemList = new ArrayList<RSSFeedItem>();
		rssPaths.each { url ->
			def rootNode;
			try {
				rootNode = new XmlParser().parse(url);
			} catch (FileNotFoundException e) {
				LOG.error("File not found, please enter a valid file.", e);
			} catch (SAXException e) {
				LOG.error("Invalid path passed to XMLParser", e);
			}
			if (rootNode)
				rootNode.children().each { channel ->
					channel.children().each { node ->
						if (node.name() == ITEM_NODE_NAME) {
							itemList.add(getRSSFeedItemFromNode(node));
						}
					}
				}
		}


		def byPubDateComparator = [
				compare: { rssFeedItem1, rssFeedItem2 ->
					def dateFormat = new SimpleDateFormat(RSS_FEED_PUBDATE_DATE_FORMAT, Locale.ENGLISH);
					String map1PubDate = rssFeedItem1.getPubDate();
					String map2PubDate = rssFeedItem2.getPubDate();
					def date1 = dateFormat.parse(map1PubDate);
					def date2 = dateFormat.parse(map2PubDate);
					return date1.compareTo(date2);
				}
		] as Comparator;

		itemList = itemList.sort(byPubDateComparator).reverse();

		return itemList.take(numberOfItemsToDisplay);
	}

	@Activate
	protected void activate(final Map<String, Object> properties) throws LoginException {
		//Do nothing.
	}

	@Deactivate
	protected void deactivate() {
		//continue to do nothing.
	}

	private RSSFeedItem getRSSFeedItemFromNode(Node node) {
		String nodeTitle = node.title.text();
		String nodeLink = node.link.text();
		String nodePubDate = node.pubDate.text();
		String nodeDescription = node.description.text();
		String HTML = "<li class=\"list-group-item\" data-title=\"${nodeTitle}\">Title: ${nodeTitle}<br>Link: ${nodeLink}<br>Pub Date: ${nodePubDate}<br>Description: ${nodeDescription}</li>"

		return new RSSFeedItem(nodeTitle, nodeLink, nodePubDate, nodeDescription, HTML);
	}

}
