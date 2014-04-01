package com.citytechinc.cq.harbor.servlets;

import com.citytechinc.cq.harbor.components.content.rssfeed.RSSFeed;
import com.citytechinc.cq.harbor.components.content.rssfeed.RSSFeedItem;
import com.citytechinc.cq.harbor.services.RSSFeedGeneratorService;
import com.citytechinc.cq.library.content.request.ComponentServletRequest;
import com.citytechinc.cq.library.servlets.AbstractComponentServlet;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@SlingServlet(resourceTypes = "harbor/components/content/rssfeed",
        extensions = "rssfeed",
        methods = "GET")
public class RSSFeedServlet extends AbstractComponentServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RSSFeedServlet.class);
    private static String RSS_URL_LIST_PARAMETER_NAME = "rssUrlList";
    private static String RSS_NUMBER_OF_ITEMS_TO_DISPLAY_PARAMETER_NAME = "numberOfItemsToDisplay";
    @Reference
    private RSSFeedGeneratorService rssFeedGeneratorService;

    @Override
    protected final void processGet(final ComponentServletRequest request) throws ServletException, IOException {
        SlingHttpServletResponse slingResponse = request.getSlingResponse();
        RSSFeed rssFeed = new RSSFeed(request.getComponentNode());
        List<String> rssUrlList = rssFeed.getRSSUrlList();

        if (!rssUrlList.isEmpty()) {
            LOG.debug("Url list: ", rssUrlList);
            int numberOfItemsToDisplay = rssFeed.getNumberOfFeedItemsToDisplay();
            List<RSSFeedItem> rssFeedItemList = rssFeedGeneratorService.getListOfRSSFeedItemsFromUrls(rssUrlList, numberOfItemsToDisplay);
            this.writeJsonResponse(slingResponse, rssFeedItemList);
        } else {
            throw new ServletException("rssUrlList expected, none received.");
        }
    }
}
