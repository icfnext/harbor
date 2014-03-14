package com.citytechinc.cq.harbor.services.impl

import com.citytechinc.cq.harbor.components.content.rssfeed.RSSFeedItem
import com.citytechinc.cq.harbor.services.RSSFeedGeneratorService
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Deactivate
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.resource.LoginException

import java.text.SimpleDateFormat

@Service
@Component(label = "RSS Feed Generator Service", immediate = true, metatype = true)
public final class DefaultRSSFeedGeneratorService implements RSSFeedGeneratorService {

	private static final String ITEM_NODE_NAME = "item";
	public static final String RSS_FEED_PUBDATE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

	@Override
	public final List<RSSFeedItem> getListOfRSSFeedItemsFromUrls(final List<String> rssPaths) {
		def itemList = [];
		rssPaths.each { url ->
			def rootNode = new XmlParser().parse(url);
			rootNode.children().each { channel ->
				channel.children().each { node ->
					if (node.name() == ITEM_NODE_NAME) {
						def itemMap = [
								"${RSSFeedItem.TITLE_XML_TAG_NAME}": node.title.text(),
								"${RSSFeedItem.LINK_XML_TAG_NAME}": node.link.text(),
								"${RSSFeedItem.PUB_DATE_XML_TAG_NAME}": node.pubDate.text(),
								"${RSSFeedItem.DESCRIPTION_XML_TAG_NAME}": node.description.text()
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

		return itemList;
	}

	@Activate
	protected void activate(final Map<String, Object> properties) throws LoginException {
		//Do nothing.
	}

	@Deactivate
	protected void deactivate() {
		//continue to do nothing.
	}

}
