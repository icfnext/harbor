package com.citytechinc.cq.harbor.proper.core.services.dummypage.impl;

import com.citytechinc.cq.harbor.proper.core.services.dummypage.DummyPageService;
import com.citytechinc.cq.harbor.proper.core.servlets.HomePageTestServlet;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.List;

@Service
@Component(name="Dummy Page Service", label = "Dummy Page Service")
public class DefaultDummyPageService implements DummyPageService {

    @Override
    public List<HomePageTestServlet.DummyPage> getDummyPageList(PageDecorator root) {

        List<HomePageTestServlet.DummyPage> dummyPages = Lists.newArrayList();

        for (PageDecorator currentPage : root.getChildren(new Predicate<PageDecorator>() {
            @Override
            public boolean apply(PageDecorator pageDecorator) {
                return true;
            }
        }, true)) {

            Resource pageContentResource = currentPage.getContentResource();
            ValueMap pageContentValueMap = pageContentResource.adaptTo(ValueMap.class);

            dummyPages.add(
                    new HomePageTestServlet.DummyPage(currentPage.getTitle(), pageContentValueMap.get("updateFreq", "Sometimes")));
        }

        return dummyPages;

    }

}
