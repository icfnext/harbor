package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.tabs.Tab;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = {Tab.class, ParagraphSystemContainer.class}, resourceType = DefaultTab.RESOURCE_TYPE)
public class DefaultTab implements Tab, ParagraphSystemContainer {

    public static final String RESOURCE_TYPE = "harbor/components/content/tabs/v1/tab";

    @Inject @Self
    private Resource resource;

    @Inject @Default(values = "Tab Label")
    private String label;

    @Override
    public String getId() {
        return ComponentUtils.DomIdForResourcePath(getPath());
    }

    @DialogField(fieldLabel = "Label", fieldDescription = "The label to be presented within the Tab") @TextField
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getType() {
        return getResource().getResourceType();
    }

    @Override
    public String getPath() {
        return getResource().getPath();
    }

    @Override
    public String getName() {
        return getResource().getName();
    }

    @Override
    public Classification getClassification() {
        return getResource().adaptTo(TagBasedClassification.class);
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String getParagraphSystemType() {
        return ParagraphSystemContainer.PARSYS;
    }

}