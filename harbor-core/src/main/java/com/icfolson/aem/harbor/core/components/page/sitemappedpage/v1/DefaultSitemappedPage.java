package com.icfolson.aem.harbor.core.components.page.sitemappedpage.v1;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.icfolson.aem.harbor.api.components.page.sitemappedpage.SitemappedPage;
import com.icfolson.aem.harbor.api.domain.sitemap.ChangeFrequency;
import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import javax.inject.Named;

@Component(value = "Sitemap", editConfig = false, path = "/page/common", name = "global",
    touchFileName = "touch-sitemap")
@Model(adaptables = Resource.class, adapters = SitemappedPage.class, resourceType = "wcm/foundation/components/page") //TODO: Evaluate resource type
public class DefaultSitemappedPage {

    @Inject
    private PageDecorator page;

    @DialogField(fieldLabel = "Disable Indexing",
        fieldDescription = "Indicates that attempts should be made to hide the page from robots such as search engine crawl bots.",
        name = "./icf:hiddenFromRobots", ranking = 0)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Named("icf:hiddenFromRobots")
    @Default(booleanValues = false)
    private boolean hiddenFromRobots;

    @DialogField(fieldLabel = "Change Frequency", name = "./icf:changeFrequency", ranking = 10)
    @Selection(type = Selection.SELECT)
    @Inject
    @Named("icf:changeFrequency")
    @Optional
    private ChangeFrequency changeFrequency;

    @DialogField(fieldLabel = "Priority", name = "./icf:priority", defaultValue = "0.5", ranking = 20)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "1.0", value = "1.0"),
        @Option(text = "0.9", value = "0.9"),
        @Option(text = "0.8", value = "0.8"),
        @Option(text = "0.7", value = "0.7"),
        @Option(text = "0.6", value = "0.6"),
        @Option(text = "0.5", value = "0.5"),
        @Option(text = "0.4", value = "0.4"),
        @Option(text = "0.3", value = "0.3"),
        @Option(text = "0.2", value = "0.2"),
        @Option(text = "0.1", value = "0.1"),
        @Option(text = "0.0", value = "0.0")
    })
    @Inject
    @Named("icf:priority")
    @Default(values = "0.5")
    private String priority;

    public boolean isHiddenFromRobots() {
        return hiddenFromRobots;
    }

    public ChangeFrequency getChangeFrequency() {
        return changeFrequency;
    }

    public String getPriority() {
        return priority;
    }

    public PageDecorator getPage() {
        return page;
    }

}
