package com.citytechinc.cq.harbor.proper.core.servlets;


import com.citytechinc.cq.harbor.proper.core.services.dummypage.DummyPageService;
import com.citytechinc.cq.library.content.request.ComponentServletRequest;
import com.citytechinc.cq.library.servlets.AbstractComponentServlet;
import com.day.cq.wcm.api.Page;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

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

    @Reference
    private DummyPageService dummyPageService;

    @Override
    protected void processGet(ComponentServletRequest request) throws ServletException, IOException {

        writeJsonResponse(request.getSlingResponse(), dummyPageService.getDummyPageList(request.getCurrentPage()));

    }

    public static class DummyPage {
        private final String title;
        private final String updateFreq;

        public DummyPage(String title, String updateFreq) {
            this.title = title;
            this.updateFreq = updateFreq;
        }

        public String getTitle() {
            return title;
        }

        public String getUpdateFreq() {
            return updateFreq;
        }
    }

}
