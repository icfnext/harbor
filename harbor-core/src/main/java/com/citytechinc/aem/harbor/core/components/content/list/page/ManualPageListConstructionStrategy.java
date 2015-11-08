package com.citytechinc.aem.harbor.core.components.content.list.page;

import java.util.List;

import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.api.page.PageManagerDecorator;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class ManualPageListConstructionStrategy extends AbstractComponent implements ListConstructionStrategy<PageDecorator> {

    private List<PageDecorator> pages;

    @Inject
    private PageManagerDecorator pageManagerDecorator;
    
    @DialogField(name="./paths", fieldLabel = "Paths", fieldDescription = "Path to search for nodes under.")
    @PathField(rootPath="/content")
    @MultiField
    public List<String> getPaths() {
        return getAsList("paths", String.class);
    }

    @Override
    public Iterable<PageDecorator> construct() {

        if (pages == null) {
            pages = Lists.newArrayList();

            List<String> pagePathList = getPaths();

            for (String currentPagePath : pagePathList) {
                pages.add(pageManagerDecorator.getPage(currentPagePath));
            }
        }

        return pages;

    }

}
