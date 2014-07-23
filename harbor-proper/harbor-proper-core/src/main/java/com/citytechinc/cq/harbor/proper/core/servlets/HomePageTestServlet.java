package com.citytechinc.cq.harbor.proper.core.servlets;


import com.citytechinc.cq.library.content.request.ComponentServletRequest;
import com.citytechinc.cq.library.servlets.AbstractComponentServlet;
import com.day.cq.wcm.api.Page;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@SlingServlet(
        resourceTypes = "cq:Page",
        selectors = "pagelist",
        methods = "GET",
        extensions = "json"
)
public class HomePageTestServlet extends AbstractComponentServlet {

    @Override
    protected void processGet(ComponentServletRequest request) throws ServletException, IOException {

        List<String> pageTitles = Lists.newArrayList();

        Iterator<Page> pageIterator = request.getCurrentPage().listChildren();

        while (pageIterator.hasNext()) {
            Page currentPage = pageIterator.next();

            pageTitles.add(currentPage.getTitle());
        }

        writeJsonResponse(request.getSlingResponse(), pageTitles);

    }

}
