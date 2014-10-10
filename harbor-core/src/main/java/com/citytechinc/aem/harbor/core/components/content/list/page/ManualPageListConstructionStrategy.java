package com.citytechinc.aem.harbor.core.components.content.list.page;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.api.page.PageManagerDecorator;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.google.common.collect.Lists;

import java.util.List;

public class ManualPageListConstructionStrategy extends AbstractComponent implements ListConstructionStrategy<PageDecorator> {

    private List<PageDecorator> pages;
    private PageManagerDecorator pageManagerDecorator;

    @Override
    public void init(ComponentRequest request) {
        pageManagerDecorator = getResource().getResourceResolver().adaptTo(PageManagerDecorator.class);
    }

    @Override
    public Iterable<PageDecorator> construct() {

        if (pages == null) {
            pages = Lists.newArrayList();

            List<String> pagePathList = getAsList("pages", String.class);

            for (String currentPagePath : pagePathList) {
                pages.add(pageManagerDecorator.getPage(currentPagePath));
            }
        }

        return pages;

    }

}
