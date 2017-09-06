package com.icfolson.aem.harbor.core.components.content.tabs.v1;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.api.components.content.tabs.Tab;
import com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Model(adaptables = Resource.class, adapters = Tab.class, resourceType = DefaultTab.RESOURCE_TYPE)
public class DefaultTab implements Tab {

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
        return IconUtils.iconify(label);
    }

    @Override
    public String getType() {
        return RESOURCE_TYPE;
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

    @Override
    public Classification getClassification() {
        return resource.adaptTo(TagBasedClassification.class);
    }
}