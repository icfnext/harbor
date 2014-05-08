package com.citytechinc.cq.harbor.proper.services.impl

import com.citytechinc.cq.harbor.proper.components.content.rssfeed.RSSFeedItem
import com.citytechinc.cq.harbor.proper.services.RSSFeedGeneratorService
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
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
					return date2.compareTo(date1);
				}
		] as Comparator;

		return itemList.take(numberOfItemsToDisplay);
	}

	private RSSFeedItem getRSSFeedItemFromNode(Node node) {
		String nodeTitle = node.title.text();
		String nodeLink = node.link.text();
		String nodePubDate = node.pubDate.text();
		String nodeDescription = node.description.text();
		String HTML = "<a href='${nodeLink}' class=\"list-group-item\" data-title=\"${nodeTitle}\"><h4 class='list-group-item-heading'>${nodeTitle}</h4><p class='list-group-item-text'>${nodePubDate}</p><p class='list-group-item-text'>${nodeDescription}</p></a>"

		return new RSSFeedItem(nodeTitle, nodeLink, nodePubDate, nodeDescription, HTML);
	}

}
