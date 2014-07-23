package com.citytechinc.cq.harbor.proper.core.services.dummypage;

import com.citytechinc.cq.harbor.proper.core.servlets.HomePageTestServlet;
import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

public interface DummyPageService {

    public List<HomePageTestServlet.DummyPage> getDummyPageList(PageDecorator root);

}
