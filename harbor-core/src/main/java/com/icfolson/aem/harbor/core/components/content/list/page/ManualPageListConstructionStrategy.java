package com.icfolson.aem.harbor.core.components.content.list.page;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.library.api.page.PageDecorator;
import com.icfolson.aem.library.api.page.PageManagerDecorator;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class)
public class ManualPageListConstructionStrategy extends AbstractComponent implements ListConstructionStrategy<PageDecorator> {

    @Inject
    private PageManagerDecorator pageManagerDecorator;

    private List<PageDecorator> pages;

    @DialogField(name = "./paths", fieldLabel = "Paths", fieldDescription = "Path to search for nodes under.")
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    @MultiField
    public List<String> getPaths() {
        return getAsList("paths", String.class);
    }

    @Override
    public Iterable<PageDecorator> construct() {
        if (pages == null) {
            pages = getPaths()
                .stream()
                .map(path -> pageManagerDecorator.getPage(path))
                .collect(Collectors.toList());
        }

        return pages;
    }
}
